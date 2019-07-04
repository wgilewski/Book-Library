package com.app.booklibrary.converters;

import com.app.booklibrary.model.book.Book;

public class BookJsonConverter extends JsonConverter<Book>
{
    public BookJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
