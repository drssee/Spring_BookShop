package com.example.book.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageVO {

    private Long bno;
    private String originalFileName; //클라이언트가 업로드한 파일이름
    private String storeFileName; //pk //uuid로 저장
    private String imgCategory; //이미지 카테고리 cover=c,book_imgs=i
    private String ext; //확장자
    private long size; //파일용량
    private Date regDate; //등록일

    public ImageVO(String originalFileName, String storedFileName, String imgCategory, String ext, long size) {
        this.originalFileName=originalFileName;
        this.storeFileName=storedFileName;
        this.ext=ext;
        this.size=size;
    }
}
