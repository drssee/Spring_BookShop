package com.example.orders.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersBookForm {

    private Long order_id;
    @NotNull
    @Min(1)
    private Long bno;
    @NotNull
    @Positive
    private Integer order_price;
    @NotNull
    @Positive
    private Integer order_quantity;
}
