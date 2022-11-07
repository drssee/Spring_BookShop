package com.example.book.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSaveForm implements BookForm{

    /*
    book
     */
    @NotBlank
    private String title;
    @Range(min = 5000,max = 100000)
    private int price;
    @Range(min=1,max=9999)
    private int stock;

    /*
    book_images
     */
    private MultipartFile uploadFile; //업로드 파일(단일)
    private List<MultipartFile> uploadFiles; //업로드 파일(목록)

    /*
    book_info
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date pubDate;
    @NotBlank
    private String author;
    private String description;
    @NotBlank
    private String publisher;

    /*
    category
     */
    @NotBlank
    private String categoryName;




}
