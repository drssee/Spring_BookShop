package com.example.orders.service;

import com.example.book.mapper.BookMapper;
import com.example.book.vo.BookVO;
import com.example.orders.mapper.OrdersMapper;
import com.example.orders.vo.OrderStatus;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import com.example.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper ordersMapper;
    private final BookMapper bookMapper;

    /**
     * 상품(book)의 구매
     */
    @Override
    public void buyBook(UserVO userVO, BookVO bookVO, OrdersBookVO ordersBookVO) {
        /*
        회원정책(회원등급(VIP) 쿠폰여부), 배송아이디(delivery_id) 넣어줘야함
         */

        //책을 구매하려면
        //1.userVo로 ordersVo와를 초기화 해야함
        OrdersVO ordersVO = OrdersVO
                .builder()
                .id(userVO.getId())
                .delivery_id(1L) // <--임시값 초기화
                .order_status(OrderStatus.ORDER_COMPLETE.label())
                .build();

        //2.orders 테이블에 저장
        ordersMapper.insertOrders(ordersVO);

        //3.ordersVO를 이용해 orders_bookVO의 초기화를 완료 해야함
        //orders_bookVO 초기화 완료
        ordersBookVO.setOrder_id(ordersVO.getOrder_id());
        ordersBookVO.setBno(bookVO.getBno());
        //orders_book 테이블에 저장
        ordersMapper.insert_orders_book(ordersBookVO);

        //4.주문수량만큼 상품의 재고가 줄어들어야함
        //해당 bookVO를 가져온다
        BookVO getBookVO = bookMapper.selectOne(bookVO.getBno());
        //해당 bookVO의 재고 - 주문 재고
        getBookVO.setStock(getBookVO.getStock()-ordersBookVO.getOrder_quantity());
        //해당 bookVO업데이트
        bookMapper.updateBook(getBookVO);
    }
}
