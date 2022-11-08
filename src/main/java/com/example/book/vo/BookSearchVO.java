package com.example.book.vo;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class BookSearchVO {
    String keyword;
    Integer searchedCnt;
    String option;
    @NotNull
    @Range(min=1,max=1000)
    @Builder.Default
    private Integer page=1;
    @NotNull
    @Range(min=10,max=100)
    @Builder.Default
    private Integer size=10;
    public Integer getSkip(){
        return (page-1)*size;
    }
}
