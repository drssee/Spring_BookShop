package com.example.orders.service;

import com.example.book.dao.BookDao;
import com.example.orders.dao.OrdersDao;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.common.status.OrderStatus.PAYMENT_COMPLETE;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
class OrdersServiceTest {

    @Autowired
    OrdersService ordersService;
    @Autowired(required = false)
    OrdersDao ordersDao;
    @Autowired(required = false)
    BookDao bookDao;

    String id = "user1";
    int stock = 10;

    @Test
    @DisplayName("책 구입")
    public void buyBookTest() throws Exception {
        //임의의 ordersVO,ordersBookVO 초기화
        OrdersVO ordersVO = OrdersVO.builder()
                .id("user1")
                .order_status(PAYMENT_COMPLETE.label())
                .order_date(new Date())
                .total_price(10000)
                .merchant_uid("temp1")
                .imp_uid("temp2")
                .build();
        OrdersBookVO ordersBookVO = OrdersBookVO.builder()
                .bno(1L)
                .order_price(5000)
                .order_quantity(2)
                .build();
        List<OrdersBookVO> ordersBookVOList = new ArrayList<>();
        ordersBookVOList.add(ordersBookVO);
        //ordersService 호출
        Assertions.assertDoesNotThrow(()->ordersService.buyBookFromCart(ordersVO,ordersBookVOList));
    }
}