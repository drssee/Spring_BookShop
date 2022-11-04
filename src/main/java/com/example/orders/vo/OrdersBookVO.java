package com.example.orders.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersBookVO {
    private Long order_book_id; //order_book pk
    private Long order_id; //orders pk
    private Long bno; //book pk
    private int order_price; //주문가격
    private int order_quantity; //주문수량
}
