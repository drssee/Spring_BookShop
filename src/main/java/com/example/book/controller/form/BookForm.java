package com.example.book.controller.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookForm {
    MultipartFile getUploadFile();
    List<MultipartFile> getUploadFiles();
    Long getBno();
}
