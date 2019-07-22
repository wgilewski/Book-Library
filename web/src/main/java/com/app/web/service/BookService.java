package com.app.web.service;


import com.app.model.Books;
import com.app.model.book.Book;
import com.app.model.converters.BooksJsonConverter;
import com.app.model.dto.AuthorRatingDto;
import com.app.model.dto.BookDto;
import com.app.model.dto.mappers.Mappers;
import com.app.model.exceptions.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService
{

    private final Mappers mappers = new Mappers();
    private final Books books = new Books();
    private static final String jsonFilePath = "books.json";

    private HttpRequest requestGetAllBooks()
    {
        HttpRequest request = null;
        final String apiPath = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(apiPath))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return request;
    }
    public List<Book> getAllBooksFromBooksApi()
    {
        Books books = null;
        try {
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(requestGetAllBooks(),HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            books = gson.fromJson(response.body(), Books.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if (books == null)
        {
            throw new MyException("BOOKS FROM API ERROR");
        }
        return books.getItems();
    }
    public List<Book> getAllBooksFromJsonFile()
    {
        BooksJsonConverter booksJsonConverter = new BooksJsonConverter(jsonFilePath);
        Books books = Books.builder().items(booksJsonConverter.fromJson().get().getItems()).build();

        if (books.getItems() == null)
        {
            throw new MyException("BOOK FROM JSON ERROR");
        }
        return books.getItems();
    }
    public void selectDataset(String set)
    {
        switch (set)
        {
            case "api":
            {
                books.setItems(getAllBooksFromBooksApi());
            }break;
            case "json":
            {
                books.setItems(getAllBooksFromJsonFile());
            }break;
            default:
            {
                throw new MyException("YOU CAN GET DATASET ONLY FROM API OR JSON FILE");
            }
        }
    }
    public List<BookDto> getAllBooksDto()
    {
        return books.getItems().stream().map(book -> mappers.fromBookToBookDto(book)).collect(Collectors.toList());
    }




    public Optional<BookDto> getBookDtoByIsbn(String isbn)
    {
        if ( isbn == null)
        {
            throw new MyException("ISBN VALUE IS NULL");
        }

        Optional<BookDto> book = books.getItems().stream()
                .filter(book1 -> book1.getVolumeInfo().getIndustryIdentifiers()
                        .stream()
                        .anyMatch(industryIdentifiers -> industryIdentifiers.getType().equals("ISBN_13") &&
                                industryIdentifiers.getIdentifier().equals(isbn)))
                .findFirst()
                .map(book1 -> mappers.fromBookToBookDto(book1));

        if (!book.isPresent())
        {
            throw new MyException("404 - BOOK WITH GIVEN ID IS NOT PRESENT IN DATASET");
        }

        return book;
    }
    public List<BookDto> getBooksDtoByCategory(String category)
    {

        return books.getItems().stream()
                .filter(book -> book.getVolumeInfo().getCategories() != null &&
                        Arrays.asList(book.getVolumeInfo().getCategories()).contains(category))
                .map(book -> mappers.fromBookToBookDto(book))
                .collect(Collectors.toList());
    }



    public List<AuthorRatingDto> getAllAuthorsAverageRatingDto()
    {
        List<AuthorRatingDto> authorsRating = new ArrayList<>();

        getAllAuthors().stream()
                .forEach(s -> authorsRating.add(AuthorRatingDto
                        .builder()
                        .author(s)
                        .averageRating(countAverageRating(s))
                        .build()
                        )
                );

        if (authorsRating.size() == 0)
        {
            throw new MyException("AUTHOR RATING DTO IS NULL");
        }

        return authorsRating.stream()
                .sorted(Comparator
                        .comparingDouble(AuthorRatingDto::getAverageRating)
                        .reversed())
                .collect(Collectors.toList());
    }
    public double countAverageRating(String author)
    {
        List<Double> averages =  books.getItems().stream()
                .filter(book -> book.getVolumeInfo().getAuthors() != null)
                .filter(book -> book.getVolumeInfo().getAverageRating() != null)
                .filter(book -> Arrays.asList(book.getVolumeInfo().getAuthors()).contains(author))
                .map(book -> book.getVolumeInfo().getAverageRating())
                .collect(Collectors.toList());

        DoubleSummaryStatistics stats = averages.stream()
                .mapToDouble((x) -> x)
                .summaryStatistics();

        return stats.getAverage();
    }
    public Set<String> getAllAuthors()
    {
        Set<String> authors = new HashSet<>();
        books.getItems().stream()
                .filter(book -> book.getVolumeInfo().getAuthors() != null)
                .forEach(book -> authors.addAll(Arrays.asList(book.getVolumeInfo().getAuthors())));

        if (authors.size() == 0)
        {
            throw new MyException("GET ALL AUTHORS ERROR");
        }

        return authors;
    }



}
