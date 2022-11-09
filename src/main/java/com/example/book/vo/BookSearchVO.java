package com.example.book.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSearchVO {
    String keyword; //검색 키워드
    Integer searchedCnt; //검색한 갯수
    String option; //검색 옵션 제목/카테고리/제목+내용
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
