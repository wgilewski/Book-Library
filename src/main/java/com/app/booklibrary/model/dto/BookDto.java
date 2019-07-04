package com.app.booklibrary.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto
{
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    private String thumbNailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;
    private String[] authors;
    private String[] categories;
}
