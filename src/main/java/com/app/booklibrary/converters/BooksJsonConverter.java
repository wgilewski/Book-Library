package com.app.booklibrary.converters;

import com.app.booklibrary.model.Books;

public class BooksJsonConverter extends JsonConverter<Books> {
    public BooksJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
