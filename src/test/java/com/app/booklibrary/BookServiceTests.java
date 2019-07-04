package com.app.booklibrary;


import com.app.booklibrary.model.book.Book;
import com.app.booklibrary.model.dto.BookDto;
import com.app.booklibrary.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class BookServiceTests
{
    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("get all booksDto from Api")
    public void test1()
    {
        Mockito
                .when(bookService.getAllBooksFromBooksApi())
                .thenReturn(List.of(
                        Book.builder().build(),
                        Book.builder().build(),
                        Book.builder().build(),
                        Book.builder().build()

                ));
        List<Book> books = bookService.getAllBooksFromBooksApi();
        Assertions.assertEquals(4,books.size());
    }

    @Test
    @DisplayName("get all booksDto from Json")
    public void test2()
    {
        Mockito
                .when(bookService.getAllBooksFromJsonFile())
                .thenReturn(List.of(
                        Book.builder().build(),
                        Book.builder().build(),
                        Book.builder().build(),
                        Book.builder().build()
                ));


        List<Book> books = bookService.getAllBooksFromJsonFile();
        Assertions.assertEquals(4,books.size());
    }

    @Test
    @DisplayName("get bookDto by isbn number")
    public void test3()
    {
        Mockito
                .when(bookService.getBookDtoByIsbn("1"))
                .thenReturn(Optional.of(
                        BookDto.builder().isbn("1").build())
                );
        BookDto bookDto = bookService.getBookDtoByIsbn("1").get();
        Assertions.assertEquals("1",bookDto.getIsbn());
    }


    @Test
    @DisplayName("get booksDto by category")
    public void test4()
    {
        String[] categories = {"java","java1"};
        Mockito
                .when(bookService.getBooksDtoByCategory("java"))
                .thenReturn(List.of(
                        BookDto.builder().categories(categories).build(),
                        BookDto.builder().categories(categories).build(),
                        BookDto.builder().categories(categories).build(),
                        BookDto.builder().categories(categories).build()
                ));

        List<BookDto> books = bookService.getBooksDtoByCategory("java");
        Assertions.assertEquals(4,books.size());
        Assertions.assertEquals(books.get(1).getCategories(),categories);
    }





}


