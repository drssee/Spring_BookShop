package com.example.orders.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersForm {

    private Long order_id;
    @NotBlank
    private String id;//유저아이디
    private Integer delivery_id;//배송id
    private String order_status;//주문상태
    private Date order_date;//주문날짜
    @NotNull
    @Positive
    @Min(100)
    private Integer total_price;//총 주문가격
    @NotNull
    private String merchant_uid;//주문id
    @NotNull
    private String imp_uid;//결제id
    @NotNull
    private List<OrdersBookForm> ordersBookForms;
    private boolean fromCart;
}
