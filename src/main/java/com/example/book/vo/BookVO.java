package com.example.book.vo;

import com.example.book.controller.form.BookSaveForm;
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
    private int stock;
    //////////
    private String cover;
    private String publisher;

    public BookVO(BookSaveForm form){
        this.title=form.getTitle();
        this.pubDate=form.getPubDate();
        this.author=form.getAuthor();
        this.description= form.getDescription();
        this.price= form.getPrice();
        this.stock=form.getStock();
        this.cover=form.getCover();
        this.publisher= form.getPublisher();
    }
}
