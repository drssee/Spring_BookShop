package com.example.book.service;

import com.example.book.mapper.BookMapper;
import com.example.book.vo.BookVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class BookServiceTest {

    @Autowired
    BookService bookService;
    @Autowired(required = false)
    BookMapper bookMapper;

    Long bno = 76828L;

    @Test
    @DisplayName("책 등록")
    void registBookTest(){
        //given
        //임의의 bno를 이용해 bookVo를 가져온다
        BookVO bookVO = bookMapper.selectOne(bno);
        assertNotNull(bookVO);

        //when
        //책 등록
        BookVO bookVO1 = bookService.registBook(bookVO);
        assertNotNull(bookVO1);

        //then
        //임의의 bookVO와 등록한 bookVO1의 isbn은 같아야 한다(같은책)
        assertEquals(bookVO.getIsbn(),bookVO1.getIsbn());
    }

    @Test
    @DisplayName("책 수정")
    void updateBookTest(){
        //given
        //임의의 bno를 이용해 bookVo,bookVo1를 가져온다
        BookVO bookVO = bookMapper.selectOne(bno);
        assertNotNull(bookVO);

        //when
        //bookVo의 내용을 임의의 값으로 업데이트
        int stock = 0;
        int price = 10000;
        bookVO.setStock(stock);
        bookVO.setPrice(price);

        //then
        //업데이트는 성공해야함
        assertTrue(bookService.updateBook(bookVO));
        BookVO bookVO1 = bookMapper.selectOne(bno);
        //업데이트된 bookVo1의 bookVO1.stock, bookVO1.price는 임의의 값 stock,price와 같아야 한다
        assertEquals(stock,bookVO1.getStock());
        assertEquals(price,bookVO1.getPrice());
    }

    @Test
    @DisplayName("책 삭제")
    void removeBookTest(){
        //임의의 bno가 주어졌을때 삭제에 성공해야함
        assertDoesNotThrow(()->{
            bookService.removeBook(bno);
        });
    }
}
