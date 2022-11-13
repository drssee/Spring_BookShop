package com.example.orders.dao;

import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;

public interface OrdersDao {
    /**
     * 오더 등록
     */
    Long insertOrders(OrdersVO ordersVO);

    /**
     * 오더 (단일)조회
     */
    OrdersVO selectOrders(Long order_id);

    /**
     * 오더_상품 등록
     */
    int insert_orders_book(OrdersBookVO ordersBookVO);

    /**
     * 오더_상품 (단일)조회
     */
    OrdersBookVO selectOrdersBook(Long order_book_id);
}
