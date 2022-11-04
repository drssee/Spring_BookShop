package com.example.book.dao;

import com.example.book.mapper.BookMapper;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
class BookMapperTest {

    @Autowired
    DataSource dataSource;
    @Autowired(required = false)
    BookMapper bookMapper;

    Long bno = 76828L;

    @Test
    @DisplayName("dataSource연결 테스트")
    public void connTest1(){
        //bookdao는 null이 아니어야함
        assertNotNull(dataSource);
    }

    @Test
    @DisplayName("bookMapper연결 테스트")
    public void connTest2(){
        //bookdao는 null이 아니어야함
        assertNotNull(bookMapper);
    }

    @Test
    @DisplayName("selectOne 테스트")
    void selectOneTest(){
        //임의의 bno를 이용해 bookVO를 불러온다,불러오지 못하면 실패
        BookVO bookVO = null;
        try {
            bookVO = bookMapper.selectOne(bno);
        } catch (Exception e) {
            fail("selectOne 실패");
        }

        //입력한 bno와 가져온 bookvo의 bno는 같아야함
        assertEquals(bno, bookVO.getBno());
    }

    @Test
    @DisplayName("insertBook 테스트")
    void insertBookTest(){
        //given
        String title="title";
        String author="author";
        int price = 0;
        BookVO bookVO = BookVO
                .builder()
                .title(title)
                .author(author)
                .price(price)
                .build();
        //when
        //insertbook은 성공해야 하고 rowcnt==1 , 전달한 bookVO와 저장된 getBookVO는 같은 값이어야한다
        assertEquals(1,bookMapper.insertBook(bookVO));
        BookVO getBookVO = bookMapper.selectOne(bookVO.getBno());
//        assertEquals(bookVO,getBookVO);
    }

    @Test
    @DisplayName("insertCategoryBook 테스트")
    void insertCategoryBookTest(){
        //임의의 bno를 이용해 ,categoryid를 초기화
        int categoryId=1;
        //결과 rowCnt = 1 이면 통과
        assertEquals(1,bookMapper.insertCategoryBook(bno,categoryId));
    }

    @Test
    @DisplayName("updateBook 테스트")
    void updateCategoryTest(){
        //임의의 bno를 초기화후 bookVo를 가져옴
        BookVO bookVO = bookMapper.selectOne(bno);
        //수정할 가격과 수량을 가져와 bookVO를 수정
        int price = 10000;
        int stock = 0;
        bookVO.setPrice(price);
        bookVO.setStock(stock);
        //수정한 bookvo의 업데이트는 성공 해야 하고, 칼럼들은 수정값으로 변경되어야함
        assertEquals(1,bookMapper.updateBook(bookVO));
        BookVO bookVO1 = bookMapper.selectOne(bno);
        assertEquals(bookVO.getPrice(),bookVO1.getPrice());
        assertEquals(bookVO.getStock(),bookVO1.getStock());
    }

    @Test
    @DisplayName("deleteBook 테스트")
    void deleteBookTest(){
        //임의의 bno를 초기화
        //삭제는 성공해야함
        assertEquals(1,bookMapper.deleteBook(bno));
    }

    @Test
    @DisplayName("selectCategory_name_id 테스트")
    void selectCategory_name_idTest(){
        //임의의 bno를 초기화
        //조회는 성공해야함(0보다 커야함)
        assertTrue(bookMapper.selectCategory_name_id(bno)>=0);
    }

    @Test
    @DisplayName("deleteCategory_book 테스트")
    void deleteCategory_bookTest(){
        //임의의 bno를 초기화
        //삭제는 성공해야함
        assertEquals(1,bookMapper.deleteBook(bno));
    }

    @Test
    @DisplayName("insertCategory 성공 테스트")
    void insertCategoryTest1(){
        //임의의 카테고리 초기화 (기존 카테고리 번호,이름과 중복이 아닐시)
        CategoryVO categoryVO = new CategoryVO(27,"존재하지 않던 카테고리");
        assertEquals(1,bookMapper.insertCategory(categoryVO));
    }

    @Test
    @DisplayName("insertCategory 실패 테스트")
    void insertCategoryTest2(){
        //임의의 카테고리 초기화 (기존 카테고리 번호,이름과 중북이면 rowCnt=0)
        CategoryVO categoryVO = new CategoryVO(1,"건강");
        assertEquals(0,bookMapper.insertCategory(categoryVO));
    }

    @Test
    @DisplayName("selectCategory_name 테스트")
    void selectCategory_nameTest(){
        //임의의 category_name_id 초기화
        int category_name_id = 1;
        //가져온 카테고리 이름은 "건강" 이어야함(실제 저장된값)
        assertEquals("건강",bookMapper.selectCategory_name(1));
    }


}