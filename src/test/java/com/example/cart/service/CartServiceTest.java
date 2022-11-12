package com.example.cart.service;


import com.example.cart.dao.CartDao;
import com.example.cart.vo.CartVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class CartServiceTest {

    @Autowired(required = false)
    CartDao cartDao;

    String id = "user1";
    Long bno = 1001L;
    int quantity = 1;

    @Test
    @DisplayName("카트 저장")
    void saveCartTest(){
        //임의의 cartVO 초기화
        CartVO cartVO = CartVO
                .builder()
                .id(id)
                .bno(bno)
                .quantity(quantity)
                .build();
        Assertions.assertDoesNotThrow(()->cartDao.insertCart(cartVO));
    }
}
