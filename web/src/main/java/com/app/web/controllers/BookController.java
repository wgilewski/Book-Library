package com.app.web.controllers;


import com.app.model.dto.AuthorRatingDto;
import com.app.model.dto.BookDto;
import com.app.web.service.BookService;
import lombok.RequiredArgsConstructor;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Api(
        name = "Book API",
        description = "Provides a list of methods that manage books")
public class BookController
{
    private final BookService bookService;




    @GetMapping("/selectDataset/{set}")
    @ApiMethod(description = "Select dataset from api or json file")
    public ResponseEntity<String> selectDataset(@ApiPathParam(name = "set") @PathVariable String set)
    {
        bookService.selectDataset(set);

        if (set.equals("api"))
        {
            return ResponseEntity.ok("DATASET FROM API HAS BEEN SELECTED PROPERLY \n" +
                    "NOW YOU CAN CHECK ENDPOINTS WITH SELECTED DATASET");
        }
        else
        {
            return ResponseEntity.ok("DATASET FROM JSON HAS BEEN SELECTED PROPERLY \n" +
                    "NOW YOU CAN CHECK ENDPOINTS WITH SELECTED DATASET");
        }

    }

    @GetMapping
    @ApiMethod(description = "Get all books from chosen dataset")
    public ResponseEntity<List<BookDto>> getAllBooks()
    {
        List<BookDto> books = bookService.getAllBooksDto();

        if (books == null && books.size() == 0)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{isbnNumber}")
    @ApiMethod(description = "Get one book from dataset by id number")
    public ResponseEntity<BookDto> getBookByIsbnNumber(@ApiPathParam(name = "isbnNumber") @PathVariable String isbnNumber)
    {
        Optional<BookDto> book = bookService.getBookDtoByIsbn(isbnNumber);

        if (book.isPresent())
        {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/category/{category}")
    @ApiMethod(description = "Get all books from dataset which contains category")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@ApiPathParam(name = "category") @PathVariable String category)
    {

        List<BookDto> books = bookService.getBooksDtoByCategory(category);

        if (books == null && books.size() == 0)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);

    }

    @GetMapping("/rating")
    @ApiMethod(description = "Get author rating and sort descending")
    public ResponseEntity<List<AuthorRatingDto>> getAuthorsRating()
    {
        List<AuthorRatingDto> authorsRating = bookService.getAllAuthorsAverageRatingDto();

        if (authorsRating == null && authorsRating.size() == 0)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorsRating);
    }


}
