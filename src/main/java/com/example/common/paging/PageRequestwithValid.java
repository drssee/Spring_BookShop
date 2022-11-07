package com.example.common.paging;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PageRequestwithValid {
    @NotNull
    @Range(min=1,max=1000)
    @Builder.Default
    private Integer page=1;

    @NotNull
    @Range(min=9,max=100)
    @Builder.Default
    private Integer size=9;
    public Integer getSkip(){
        return (page-1)*size;
    }
}
