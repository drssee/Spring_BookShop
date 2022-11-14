package com.example.orders.controller.dto;

import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrdersBookDto {
    private Long order_id; //orders pk
    private String id; //유저아이디
    private Long delivery_id; //delivery pk
    private String order_status; //주문상태 //주문완료,결제완료,,배송중,배송완료
    private Date order_date;//주문날짜
    private int total_price;//주문 총금액
    private String merchant_uid;//주문id
    private String imp_uid;//결제id
    private List<OrdersBookVO> ordersBookVOList;//order_id -> ordersBookVOList


    /*
    init
     */
    public OrdersBookDto(OrdersVO ordersVO,List<OrdersBookVO> ordersBookVOList){
        this.order_id=ordersVO.getOrder_id();
        this.id=ordersVO.getId();
        this.delivery_id=ordersVO.getDelivery_id();
        this.order_status=ordersVO.getOrder_status();
        this.order_date=ordersVO.getOrder_date();
        this.total_price=ordersVO.getTotal_price();
        this.merchant_uid=ordersVO.getMerchant_uid();
        this.imp_uid=ordersVO.getImp_uid();
        this.ordersBookVOList=ordersBookVOList;
    }
    private OrdersBookDto(){}
}
