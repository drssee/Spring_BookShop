package com.example.book.controller.form;

import com.example.book.controller.PageSetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSaveForm implements PageSetable {
    /**
     * bookVO+categoryVO+pageRequest
     */
    @NotBlank
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;

    @NotBlank
    private String author;

    private String description;

    @Range(min = 5000,max = 100000)
    private int price;

    @Range(min=1,max=9999)
    private int stock;

    private String cover;

    @NotBlank
    private String publisher;

    //////////////

    @NotBlank
    private String categoryName;

    //////////////

    @NotNull
    @Min(1) @Max(1000)
    @Builder.Default
    private Integer page=1;
    @NotNull
    @Min(9) @Max(100)
    @Builder.Default
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
