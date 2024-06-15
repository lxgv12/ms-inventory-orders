package com.unir.ms_inventory_orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
    private Long id;
    private String name;
    private String price;
    private String author;
    private String publisher;
    private String published;
    private String genre;
    private String language;
    private String iSBN;
    private String format;
}
