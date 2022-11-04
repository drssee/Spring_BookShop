package com.example.orders.mapper;

import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class OrdersMapperTest {

    @Autowired(required = false)
    OrdersMapper ordersMapper;

    String id = "user1";

    @Test
    @DisplayName("insertOrder 테스트")
    public void insertOrderTest() throws Exception {
        //given
        //임의의 ordersVo를 초기화
        OrdersVO ordersVO = OrdersVO
                .builder()
                .id(id)
                .delivery_id(1L)
                .order_status("주문완료")
                .build();
        //when,then
        //insertOrders는 성공해야함 (rowCnt = 1)
        assertThat(1).isEqualTo(ordersMapper.insertOrders(ordersVO));
    }

    @Test
    @DisplayName("insert_order_book 테스트")
    public void insert_orders_bookTest() throws Exception {
        //given
        //임의의 ordersbookVO를 초기화
        OrdersBookVO ordersBookVO = OrdersBookVO
                .builder()
                .order_id(1L)
                .bno(1L)
                .order_price(1000)
                .order_quantity(1)
                .build();

        //when,then
        //insertOrderBook은 성공해야함 (rowCnt=1)
        assertThat(1).isEqualTo(ordersMapper.insert_orders_book(ordersBookVO));
    }
}
