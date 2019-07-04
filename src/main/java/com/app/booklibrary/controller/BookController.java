package com.app.booklibrary.controller;

import com.app.booklibrary.model.dto.AuthorRatingDto;
import com.app.booklibrary.model.dto.BookDto;
import com.app.booklibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController
{
    private final BookService bookService;

    @GetMapping("/selectDataset/{set}")
    public ResponseEntity<String> selectDataset(@PathVariable String set)
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
    public ResponseEntity<BookDto> getBookByIsbnNumber(@PathVariable String isbnNumber)
    {
        Optional<BookDto> book = bookService.getBookDtoByIsbn(isbnNumber);

        if (book.isPresent())
        {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@PathVariable String category)
    {

        List<BookDto> books = bookService.getBooksDtoByCategory(category);

        if (books == null && books.size() == 0)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);

    }

    @GetMapping("/rating")
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
