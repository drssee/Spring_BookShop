package com.example.book.review.service;

import com.example.common.dto.BnoDto;
import com.example.common.paging.PageResponse;
import com.example.review.service.ReviewService;
import com.example.review.vo.ReviewVO;
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
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    Long bno = 1001L;
    Long rno = 20L;

    @Test
    @DisplayName("리뷰 리스트 조회")
    void getReviewsTest(){
        //임의의 bno로 리뷰리스트 조회
        PageResponse<ReviewVO> pages = reviewService.getReviewPages(BnoDto.builder().bno(bno).page(1).size(10).build());
        assertTrue(pages.getSize()>0);
    }

    @Test
    @DisplayName("리뷰 조회")
    void getReviewTest(){
        assertNotNull(reviewService.getReview(rno));
    }

    @Test
    @DisplayName("리뷰 업데이트")
    void updateTest(){
        //given
        //업데이트할 Content
        String updateStr = "updated_review";
        //when
        //임의의 rno의 리뷰를 가져온후 업데이트
        ReviewVO review = reviewService.getReview(rno);
        review.setContent(updateStr);
        assertEquals(1,reviewService.updateReview(review));
        //then
        //업데이트후 가져온 리뷰의 Content는 업데이트 되어야한다
        ReviewVO updatedReview = reviewService.getReview(rno);
        assertEquals(updateStr,updatedReview.getContent());
    }

    @Test
    @DisplayName("리뷰 삭제")
    void deleteTest(){
        //임의의 rno에 해당하는 리뷰 삭제
        assertTrue(reviewService.deleteReview(rno)>0);
    }
}
