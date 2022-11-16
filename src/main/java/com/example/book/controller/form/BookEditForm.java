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
import javax.validation.constraints.Positive;
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
    @NotNull(message = "가격을 입력해주세요")
    @Positive
    @Range(min = 5000,max = 100000)
    private Integer price;
    @NotNull(message = "재고를 입력해주세요")
    @Positive
    @Range(min=1,max=9999)
    private Integer stock;

    /*
    book_images
     */
    private MultipartFile uploadFile; //업로드 파일(단일)
    private List<MultipartFile> uploadFiles; //업로드 파일(목록)

    /*
    book_info
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "출판일을 입력해주세요")
    private Date pubDate;
    @NotBlank(message = "저자를 입력해주세요")
    private String author;
    @NotBlank(message = "상세설명을 입력해주세요")
    private String description;
    @NotBlank(message = "출판사를 입력해주세요")
    private String publisher;

}
