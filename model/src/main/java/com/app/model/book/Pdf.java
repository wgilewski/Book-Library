package com.app.model.book;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Pdf
{
    private boolean isAvailable;
    private String acsTokenLink;
}
