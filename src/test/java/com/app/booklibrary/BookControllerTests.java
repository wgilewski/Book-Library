package com.app.booklibrary;

import com.app.booklibrary.controller.BookController;
import com.app.booklibrary.model.dto.AuthorRatingDto;
import com.app.booklibrary.model.dto.BookDto;
import com.app.booklibrary.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTests
{
    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get all books")
    public void test1() throws Exception
    {
        Mockito
                .when(bookService.getAllBooksDto())
                .thenReturn(Arrays.asList(
                        BookDto
                                .builder()
                                .build(),
                        BookDto
                                .builder()
                                .build()
                ));
        ResultActions resultActions = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/books")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
        System.out.println(resultActions.andReturn().getResponse().getStatus());
    }



    @Test
    @DisplayName("get one book")
    public void test3() throws Exception {

        Mockito
                .when(bookService.getBookDtoByIsbn("12"))
                .thenReturn(Optional.of(BookDto.builder().isbn("12").title("A").build()));


        ResultActions resultActions = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/{isbnNumber}", "12")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("A")));
        System.out.println(resultActions.andReturn().getResponse().getStatus());
    }


    @Test
    @DisplayName("get all books by category")
    public void test4() throws Exception
    {
        String[] category = {"java"};

        Mockito
                .when(bookService.getBooksDtoByCategory("java"))
                .thenReturn(Arrays.asList(
                        BookDto
                                .builder()
                                .isbn("12")
                                .categories(category)
                                .build(),
                        BookDto
                                .builder()
                                .isbn("13")
                                .categories(category)
                                .build(),
                        BookDto
                                .builder()
                                .isbn("14")
                                .categories(category)
                                .build()
                ));


        ResultActions resultActions = mockMvc
                .perform(
                        MockMvcRequestBuilders
                        .get("/books/category/{category}","java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(3)));

        System.out.println(resultActions.andReturn().getResponse().getStatus());
    }


    @Test
    @DisplayName("get authors average rating")
    public void test5() throws Exception
    {
        Mockito
                .when(bookService.getAllAuthorsAverageRatingDto())
                .thenReturn(
                        List.of(
                                AuthorRatingDto.builder().author("a").averageRating(4.2).build(),
                                AuthorRatingDto.builder().author("b").averageRating(3.2).build(),
                                AuthorRatingDto.builder().author("c").averageRating(2.2).build(),
                                AuthorRatingDto.builder().author("d").averageRating(1.2).build()
                                )

                );

        ResultActions resultActions = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/books/rating")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(4)));

        System.out.println(resultActions.andReturn().getResponse().getStatus());

    }








}
