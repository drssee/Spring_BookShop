package com.example.orders.service;

import com.example.orders.controller.dto.OrdersBookDto;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface OrdersService {


    /**
     * 상품(book)의 구매
     */
    void buyBook
    (OrdersVO ordersVO, List<OrdersBookVO> ordersBookVOList,boolean isFromCart)
            throws ResponseStatusException;

    /**
     * 오더 조회
     */
    List<OrdersVO> getOrders(String id);

    /**
     * 오더 조회(id)
     */
    OrdersVO getOrders(Long order_id);

    /**
     * 오더_상품 조회
     */
    List<OrdersBookVO> getOrdersBook(Long order_id);

    /**
     * 오더_상품dto 조회
     */
    List<OrdersBookDto> getOrdersBookDtos(String id);

    /**
     * 오더 취소
     */
    void cancelOrders(Long order_id);
}
