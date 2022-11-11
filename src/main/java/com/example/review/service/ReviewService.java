package com.example.review.service;

import com.example.common.dto.BnoDto;
import com.example.common.paging.PageResponse;
import com.example.review.vo.ReviewVO;

public interface ReviewService {
    /**
     * 해당 bno의 리뷰를 조회
     */
    PageResponse<ReviewVO> getReviewPages(BnoDto bnoDto);

    /**
     * 해당 rno의 리뷰를 조회(단일)
     */
    ReviewVO getReview(Long rno);

    /**
     * 해당 bno에 리뷰를 작성(부모 댓글)
     */
    void writeParentReview(ReviewVO reviewVO);

    /**
     * 해당 bno에 리뷰를 작성(자식 답글)
     */
    void writeChildReview(ReviewVO reviewVO);

    /**
     * 해당 rno의 리뷰를 업데이트
     */
    int updateReview(ReviewVO reviewVO);

    /**
     * 해당 rno의 리뷰를 삭제
     */
    int deleteReview(Long rno);
}
