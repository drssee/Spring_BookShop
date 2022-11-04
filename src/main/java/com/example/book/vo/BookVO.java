package com.example.book.vo;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVO {

    private Long bno;
    private String isbn;
    private String title;
    private Date pubDate;
    private String author;
    private String description;
    private int price;
    //////////
    private int mileage;
    private int stock;
    //////////
    private String cover;
    private String publisher;

    public int getMileage(){
        return price/10;
    }
}
