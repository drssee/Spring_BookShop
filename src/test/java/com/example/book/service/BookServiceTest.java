package com.example.book.service;

import com.example.book.dao.BookDao;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class BookServiceTest {

    @Autowired
    BookService bookService;
    @Autowired(required = false)
    BookDao bookDao;

    Long bno = 1L;

    @Test
    @DisplayName("책 등록")
    void registBookTest(){
        //given
        //임의의 bno를 이용해 bookVo,CategoryVo를 가져온다
        BookVO bookVO = bookDao.selectBook(bno);
        assertNotNull(bookVO);
        CategoryVO categoryVO = new CategoryVO(
                bookDao.selectCategory_name(bookDao.selectCa_Books_categoryNameId(bno))
        );

        //when,then
        //책 등록
        BookVO bookVO1 = bookService.registerBook(bookVO,categoryVO,new ArrayList<>());//빈 테스트용 imageVOs 생성
        assertNotNull(bookVO1);
    }

    @Test
    @DisplayName("책 수정")
    void updateBookTest(){
        //given
        //임의의 bno를 이용해 bookVo,bookVo1를 가져온다
        BookVO bookVO = bookDao.selectBook(bno);
        assertNotNull(bookVO);

        //when
        //bookVo의 내용을 임의의 값으로 업데이트
        int stock = 0;
        int price = 10000;
        bookVO.setStock(stock);
        bookVO.setPrice(price);

        //then
        //업데이트는 성공해야함
        assertDoesNotThrow(()->bookService.updateBook(bookVO));
        BookVO bookVO1 = bookDao.selectBook(bno);
        //업데이트된 bookVo1의 bookVO1.stock, bookVO1.price는 임의의 값 stock,price와 같아야 한다
        assertEquals(stock, bookVO1.getStock());
        assertEquals(price, bookVO1.getPrice());
    }

    @Test
    @DisplayName("책 삭제")
    void removeBookTest(){
        //임의의 bno가 주어졌을때 삭제에 성공해야함
        assertDoesNotThrow(()->{
            bookService.removeBook(bno);
        });
    }

    @Test
    @DisplayName("책 리스트 조회")
    void getBooksTest(){
        //pageRequest를 이용해 선택한 페이지의 pageResponse를 가져온다 page=1,size=9
        PageRequest pageRequest = PageRequest.builder().build();
        PageResponse<BookVO> pageResponse_BookVO = bookService.getBooks(pageRequest);
        //pageResponse_BookVO = PageResponse(page=1, size=9, total=10000, start=1, end=10, last=1112, showPrev=false, showNext=true

        //가져온 pageResponse값은 유효한 값이어야한다
        assertEquals(pageResponse_BookVO.getSize(),pageResponse_BookVO.getPageList().size());
        assertEquals(pageResponse_BookVO.getPage(),pageResponse_BookVO.getPage());
        assertEquals(pageResponse_BookVO.getSize(),pageResponse_BookVO.getSize());
        assertEquals(bookDao.selectBookCnt_before(),pageResponse_BookVO.getTotal());
        assertEquals(pageRequest.getPage(),pageResponse_BookVO.getStart());
        assertEquals(pageRequest.getPage()+9,pageResponse_BookVO.getEnd());
        assertEquals((int)(Math.ceil(bookDao.selectBookCnt_before()/(double)pageRequest.getSize())),pageResponse_BookVO.getLast());
    }
}
