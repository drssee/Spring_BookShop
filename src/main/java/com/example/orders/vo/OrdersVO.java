package com.example.orders.vo;

import com.example.orders.controller.form.OrdersForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersVO {
    private Long order_id; //orders pk
    private String id; //유저아이디
    private Long delivery_id; //delivery pk
    private String order_status; //주문상태 //주문완료,결제완료,,배송중,배송완료
    private Date order_date;//주문날짜
    private int total_price;//주문 총금액
    private String merchant_uid;//주문id
    private String imp_uid;//결제id

    public OrdersVO(OrdersForm ordersForm){
        this.id=ordersForm.getId();
        this.order_date=ordersForm.getOrder_date();
        this.total_price=ordersForm.getTotal_price();
        this.merchant_uid=ordersForm.getMerchant_uid();
        this.imp_uid=ordersForm.getImp_uid();
    }
}
