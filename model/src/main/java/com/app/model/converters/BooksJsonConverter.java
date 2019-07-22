package com.app.model.converters;

import com.app.model.Books;

public class BooksJsonConverter extends JsonConverter<Books> {
    public BooksJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
