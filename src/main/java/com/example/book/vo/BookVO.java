package com.example.book.vo;

import com.example.book.controller.form.BookEditForm;
import com.example.book.controller.form.BookSaveForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVO {

    /*
    book
     */
    private Long bno;
    private String title;
    private int price;
    private int stock;
    private Date update_date;
    private Date reg_date;
    private Long like_cnt;

    /*
    book_images
     */
    private String storeFileName;
    private String originalFileName;
    private String imgCategory;
    private String ext;
    private int size;

    /*
    book_info
     */
    private Date pubDate;
    private String author;
    private String description;
    private String publisher;



    public BookVO(BookSaveForm form){
        this.title=form.getTitle();
        this.pubDate=form.getPubDate();
        this.author=form.getAuthor();
        this.description= form.getDescription();
        this.price= form.getPrice();
        this.stock=form.getStock();
        this.publisher= form.getPublisher();
    }

    public BookVO(BookEditForm form){
        this.bno=form.getBno();
        this.title=form.getTitle();
        this.pubDate=form.getPubDate();
        this.author=form.getAuthor();
        this.description=form.getDescription();
        this.price=form.getPrice();
        this.stock=form.getStock();
        this.publisher=form.getPublisher();
    }
}
