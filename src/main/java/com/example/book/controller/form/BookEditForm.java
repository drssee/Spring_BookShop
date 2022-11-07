package com.example.book.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEditForm implements BookForm{

    /*
    book
     */
    private Long bno;
    private String title;
    @Range(min = 5000,max = 100000) //수정가능?
    private int price;
    @Range(min=1,max=9999) //수정가능?
    private int stock;

    /*
    book_images
     */
    private MultipartFile uploadFile; //업로드 파일(단일)
    private List<MultipartFile> uploadFiles; //업로드 파일(목록)

    /*
    book_info
     */
    private Date pubDate;
    private String author;
    private String description;
    private String publisher;

    /*
    category
     */
    private String categoryName;



}
