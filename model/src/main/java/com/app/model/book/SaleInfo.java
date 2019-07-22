package com.app.model.book;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SaleInfo
{
    private String country;
    private String saleability;
    private boolean isEbook;
}
