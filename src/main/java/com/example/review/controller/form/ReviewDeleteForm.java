package com.example.review.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReviewDeleteForm implements ReviewForm{
    @NotNull
    @Min(1)
    Long rno;//pk
    @NotNull
    @Min(1)
    Long bno;//book pk
    @NotNull
    String id;//user pk
    String content;
    @NotNull
    Long prno;//부모rno
}
