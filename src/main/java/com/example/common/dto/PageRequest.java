package com.example.common.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class PageRequest {
    @NotNull
    @Min(1) @Max(1000)
    @Builder.Default
    private Integer page=1;
    @NotNull
    @Min(9) @Max(100)
    @Builder.Default
    private Integer size=9;
    public Integer getSkip(){
        return (page-1)*size;
    }
}
