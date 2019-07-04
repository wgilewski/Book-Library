package com.app.booklibrary.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AccessInfo
{
    private String country;
    private String viewability;
    private boolean embeddable;
    private boolean publicDomain;
    private String textToSpeechPermission;
    private Epub epub;
    private Pdf pdf;
    private String webReaderLink;
    private String accessViewStatus;
    private boolean quoteSharingAllowed;
}
