package com.example.book.controller.pathvariable;

import com.example.book.controller.PageSetable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BnoDto implements PageSetable {
    /**
     * book`s pk + pageRequest
     */

    @Min(1) @Max(20000)
    private long bno;
    @NotNull
    @Min(1) @Max(1000)
    private Integer page=1;
    @NotNull
    @Min(9) @Max(100)
    private Integer size=9;
    @Override
    public void setPage(int page) {
        this.page=page;
    }
    @Override
    public void setSize(int size) {
        this.size=size;
    }
}
