package com.example.review.service;

import com.example.common.dto.BnoDto;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.review.dao.ReviewDao;
import com.example.review.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewDao reviewDao;

    /**
     * 해당 bno의 리뷰를 조회
     */
    @Override
    public PageResponse<ReviewVO> getReviewPages(BnoDto bnoDto) {
        //페이징 처리를 위한 pageResponse에 담아 리턴
        return PageResponse.<ReviewVO>withAll()
                .pageRequest(PageRequest.builder().page(bnoDto.getPage()).size(bnoDto.getSize()).build())
                .total(reviewDao.selectReviewsCnt(bnoDto.getBno()))
                .pageList(reviewDao.selectReviews(bnoDto))
                .build();
    }

    /**
     * 해당 rno의 리뷰를 조회(단일)
     */
    @Override
    public ReviewVO getReview(Long rno) {
        return reviewDao.selectReview(rno);
    }

    /**
     * 해당 bno에 리뷰를 작성(부모 댓글)
     */
    @Override
    public void writeParentReview(ReviewVO reviewVO) {
        reviewDao.insertParentReview(reviewVO);
    }

    /**
     * 해당 bno에 리뷰를 작성(자식 답글)
     */
    @Override
    public void writeChildReview(ReviewVO reviewVO) {
        reviewDao.insertChildReview(reviewVO);
    }

    /**
     * 해당 rno의 리뷰를 업데이트
     */
    @Override
    public int updateReview(ReviewVO reviewVO) {
        return reviewDao.updateReview(reviewVO);
    }

    /**
     * 해당 rno의 리뷰를 삭제
     */
    @Override
    public int deleteReview(Long rno) {
        return reviewDao.deleteReview(rno);
    }
}
