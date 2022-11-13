package com.example.orders.service;

import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;

import java.util.List;

public interface OrdersService {


    /**
     * 상품(book)의 구매
     */
    void buyBookFromCart(OrdersVO ordersVO, List<OrdersBookVO> ordersBookVOList);
}
