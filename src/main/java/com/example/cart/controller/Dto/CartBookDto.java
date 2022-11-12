package com.example.cart.controller.Dto;

import com.example.book.vo.BookVO;
import com.example.cart.vo.CartVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class CartBookDto {

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

    /*
    cart
     */
    private Long cno; //pk
    private String id; //유저id
    private int quantity; //유저가 선택한 구매갯수

    /*
    init
     */
    public CartBookDto(BookVO bookVO, CartVO cartVO){
        this.bno=bookVO.getBno();
        this.title=bookVO.getTitle();
        this.price=bookVO.getPrice();
        this.stock=bookVO.getStock();
        this.update_date=bookVO.getUpdate_date();
        this.reg_date=bookVO.getReg_date();
        this.like_cnt=bookVO.getLike_cnt();
        this.storeFileName=bookVO.getStoreFileName();
        this.originalFileName=bookVO.getOriginalFileName();
        this.imgCategory=bookVO.getImgCategory();
        this.ext=bookVO.getExt();
        this.size=bookVO.getSize();
        this.pubDate=bookVO.getPubDate();
        this.author=bookVO.getAuthor();
        this.description=bookVO.getDescription();
        this.publisher=bookVO.getPublisher();
        this.cno=cartVO.getCno();
        this.id=cartVO.getId();
        this.quantity=cartVO.getQuantity();
    }

    private CartBookDto(){}
}
