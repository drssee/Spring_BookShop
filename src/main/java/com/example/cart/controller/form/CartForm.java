package com.example.cart.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartForm {

    @NotNull
    private String id;
    @NotNull
    @Min(1)
    private Long bno;
    @NotNull
    @Range(min=1,max=10)
    private int quantity;
}
