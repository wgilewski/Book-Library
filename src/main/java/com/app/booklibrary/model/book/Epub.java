package com.app.booklibrary.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Epub
{
    private boolean isAvailable;
    private String acsTokenLink;
}
