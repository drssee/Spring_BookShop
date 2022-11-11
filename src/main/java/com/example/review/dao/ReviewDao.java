package com.example.review.dao;

import com.example.common.dto.BnoDto;
import com.example.review.vo.ReviewVO;

import java.util.List;

public interface ReviewDao {
    /**
     * 해당 bno의 모든 리뷰 조회
     */
    List<ReviewVO> selectReviews(BnoDto bnoDto);
    /**
     * 해당 rno의 리뷰를 조회(단일)
     */
    ReviewVO selectReview(Long rno);

    /**
     * 해당 bno의 전체 리뷰 갯수 조회
     */
    int selectReviewsCnt(Long bno);

    /**
     * 해당 bno에 리뷰 작성(부모 댓글)
     */
    void insertParentReview(ReviewVO reviewVO);

    /**
     * 해당 bno에 리뷰 작성(자식 답글)
     */
    void insertChildReview(ReviewVO reviewVO);


    /**
     * 해당 rno의 리뷰를 업데이트
     */
    int updateReview(ReviewVO reviewVO);

    /**
     * 해당 rno의 리뷰를 삭제
     */
    int deleteReview(Long rno);
}
