package com.example.orders.dao;

import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;

import java.util.List;

public interface OrdersDao {
    /**
     * 오더 등록
     */
    Long insertOrders(OrdersVO ordersVO);

    /**
     * 오더 조회
     */
    List<OrdersVO> selectOrders(String id);

    /**
     * 오더 조회(order_id)
     */
    OrdersVO selectOrdersByOrder_id(Long order_id);

    /**
     * 오더_상품 등록
     */
    int insert_orders_book(OrdersBookVO ordersBookVO);

    /**
     * 오더_상품 조회
     */
    List<OrdersBookVO> selectOrdersBook(Long order_id);

    /**
     * 오더 취소
     */
    int deleteOrders(Long order_id);
}
