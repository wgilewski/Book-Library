package com.app.model.book;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class VolumeInfo
{
    private String title;

    private String[] authors;

    private String publisher;
    private Double averageRating;
    private Integer ratingsCount;
    private String description;
    private Integer pageCount;
    private String publishedDate;
    private List<IndustryIdentifiers> industryIdentifiers;
    private ReadingModes readingModes;
    private String printType;
    private String[] categories;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
}
