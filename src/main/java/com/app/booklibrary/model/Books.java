package com.app.booklibrary.model;

import com.app.booklibrary.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data


public class Books
{
    private List<Book> items = new ArrayList<>();
}
