package com.example.orders.service;

import com.example.cart.dao.CartDao;
import com.example.orders.dao.OrdersDao;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.common.status.OrderStatus.PAYMENT_COMPLETE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersDao ordersDao;
    private final CartDao cartDao;

    /**
     * 상품(book)의 구매
     */
    @Override
    public void buyBookFromCart(OrdersVO ordersVO, List<OrdersBookVO> ordersBookVOList) {
        /*deliveryId*/

        //order_status 수정
        ordersVO.setOrder_status(PAYMENT_COMPLETE.label());
        //장바구니 목록 삭제
        cartDao.deleteCart(ordersVO.getId());
        //db에 주문 저장
        ordersDao.insertOrders(ordersVO);
        ordersBookVOList.forEach(ordersBookVO -> {
            ordersBookVO.setOrder_id(ordersVO.getOrder_id());
            ordersDao.insert_orders_book(ordersBookVO);
        });
    }
}
