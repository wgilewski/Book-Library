package com.app.booklibrary.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class IndustryIdentifiers
{
    private String type;
    private String identifier;
}
