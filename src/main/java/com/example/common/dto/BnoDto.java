package com.example.common.dto;

import com.example.common.paging.PageSetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BnoDto implements PageSetable {
    /**
     * book`s pk + pageRequest
     */
    @Min(1) @Max(20000)
    private long bno;

    @Builder.Default
    private Integer page=1;

    @Builder.Default
    private Integer size=10;

    @Override
    public void setPage(int page) {
        this.page=page;
    }
    @Override
    public void setSize(int size) {
        this.size=size;
    }
    @Override
    public int getPage() {
        return page;
    }
    @Override
    public int getSize() {
        return size;
    }

    public int getSkip(){
        return (page-1)*size;
    }
}
