package com.example.orders.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersVO {
    private Long order_id; //orders pk
    private String id; //유저아이디
    private Long delivery_id; //delivery pk
    private String order_status; //주문상태 //주문완료,결제완료,,배송중,배송완료
    private int total_price;
//    private String delivery_status; //배송상태 //배송중,배송완료
//    public void setTotal_price(){
//
//    }
}
