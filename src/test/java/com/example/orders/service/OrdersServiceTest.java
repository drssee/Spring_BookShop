package com.example.orders.service;

import com.example.book.mapper.BookMapper;
import com.example.book.vo.BookVO;
import com.example.orders.mapper.OrdersMapper;
import com.example.orders.vo.OrderStatus;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import com.example.user.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
class OrdersServiceTest {

    @Autowired
    OrdersService ordersService;
    @Autowired(required = false)
    OrdersMapper ordersMapper;
    @Autowired(required = false)
    BookMapper bookMapper;

    String id = "user1";
    int stock = 10;

    @Test
    @DisplayName("책 구입")
    public void buyBookTest() throws Exception {
        //given
        //임의의 user, book, orders_book 초기화
        //임의의 userVO를 초기화
        UserVO userVO = UserVO.builder().id(id).pwd(id).build();
        //임의의 bookVO를 초기화
        BookVO bookVO = bookMapper.selectOne(1L);
        //임의의 ordersbookVO를 초기화
        OrdersBookVO ordersBookVO = OrdersBookVO.builder().order_id(1L).bno(1L).order_price(1000)
                .order_quantity(stock).build();

        //비교를 위한 bookVO의 재고를 가져온다
        int beforeStock = bookVO.getStock();

        //when,then

        //책의 구입 쿼리문은 성공해야함
        Assertions.assertDoesNotThrow(()->{
            ordersService.buyBook(userVO,bookVO,ordersBookVO);
        });

        //재고(beforeStock)는 10(stock)만큼 감소 해야함
        int currentStock = beforeStock - stock;
        assertThat(currentStock)
                .isEqualTo(bookMapper.selectOne(bookVO.getBno()).getStock());

        //오더의 상태는 주문완료가 되어야함
        //주문한 orderVO를 가져온다
        OrdersBookVO getOrdersBookVO = ordersMapper.selectOrdersBook(ordersBookVO.getOrder_book_id());
        OrdersVO getOrdersVO = ordersMapper.selectOrders(getOrdersBookVO.getOrder_id());

        //가져온 get_orders_bookVO의 주문상태는 주문완료 이어야 한다
        assertThat(OrderStatus.ORDER_COMPLETE.label()).isEqualTo(getOrdersVO.getOrder_status());
    }
}