package com.example.orders.service;

import com.example.book.vo.BookVO;
import com.example.orders.vo.OrdersBookVO;
import com.example.user.vo.UserVO;

public interface OrdersService {


    /**
     * 상품(book)의 구매
     */
    void buyBook(UserVO userVO, BookVO bookVO, OrdersBookVO ordersBookVO);
}
