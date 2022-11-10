package com.example.review.controller;

import com.example.common.dto.BnoDto;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.exception.IllegalUserException;
import com.example.review.controller.form.ReviewDeleteForm;
import com.example.review.controller.form.ReviewEditForm;
import com.example.review.controller.form.ReviewForm;
import com.example.review.controller.form.ReviewSaveForm;
import com.example.review.service.ReviewService;
import com.example.review.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

import static com.example.common.status.RequestStatus.BAD_REQUEST;
import static com.example.common.status.RequestStatus.INVALID_USER;
import static com.example.common.validator.BookshopValidator.isEffectiveFormValue;
import static com.example.common.validator.BookshopValidator.validateLoginedUser;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    // *ReviewController api*

    // get /review/bookReviews/{bno} <-전체 리뷰 조회
    // post /review/bookReview/{bno} <-리뷰 작성
    // put /review/bookReview/{rno} <-리뷰 수정
    // delete /review/bookReview/{rno} <-리뷰 삭제

    @GetMapping("/bookReviews/{bno}")
    public ResponseEntity<PageResponse<ReviewVO>> getReviews(@PathVariable Long bno){
        /*
        valid
        */
        //bnoDto = bno+pageRequest 초기화
        BnoDto bnoDto = getBnoDto(bno);
        //bno 유효성 검증
        if(bnoDto.getBno()<1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }

        /*
        core
        */
        //bnoDto를 이용해서 리뷰 페이징 정보를 가져온다
        PageResponse<ReviewVO> pageResponse = reviewService.getReviewPages(bnoDto);
        //pageResponse 유효성 검증
        if(pageResponse==null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"리뷰 조회 실패");
        }
        //리뷰+페이징정보 리턴
        return ResponseEntity.ok().body(pageResponse);
    }

    @PostMapping("/bookReview")
    public ResponseEntity<ReviewVO> writeReview(@Validated @RequestBody ReviewSaveForm reviewSaveForm,
                                                              BindingResult bindingResult){
        /*
        valid
        */
        //reviewSaveForm 검증
        if(bindingResult.hasErrors()){
            if(bindingResult.getFieldError("id")!=null){
                throw new IllegalUserException(INVALID_USER.label());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }


        /*
        core
        */
        //reviewVO 초기화
        ReviewVO reviewVO = getReviewVO(reviewSaveForm);

        //리뷰작성
        writeReview(reviewSaveForm, reviewVO);
        return ResponseEntity.ok().body(reviewVO);
    }

    @PutMapping("/bookReview/{rno}")
    public ResponseEntity<ReviewVO> editReview(@Validated @RequestBody ReviewEditForm reviewEditForm,
                                               BindingResult bindingResult, HttpServletRequest request){
        /*
        valid
        */
        //reviewEditForm 검증
        if(bindingResult.hasErrors()){
            if(bindingResult.getFieldError("id")!=null){
                throw new IllegalUserException(INVALID_USER.label());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }
        //rno로 review값을 조회해 form값과 검증(bno,id 일치하는지)
        if(!isEffectiveFormValue(
                reviewService.getReview(reviewEditForm.getRno()),reviewEditForm)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }
        //로그인 유저가 맞는지 검증
        if(!validateLoginedUser(reviewEditForm.getId(),request)) {
            throw new IllegalUserException(INVALID_USER.label());
        }


        /*
        core
        */
        //reviewVO 초기화
        ReviewVO reviewVO = getReviewVO(reviewEditForm);

        //해당 rno의 리뷰를 업데이트
        reviewService.updateReview(reviewVO);
        return ResponseEntity.ok(reviewVO);
    }

    @DeleteMapping("/bookReview/{rno}")
    public ResponseEntity<String> deleteReview(@Validated @RequestBody ReviewDeleteForm reviewDeleteForm,
                                               BindingResult bindingResult, HttpServletRequest request){
        /*
        valid
        */
        //reviewEditForm 검증
        if(bindingResult.hasErrors()){
            if(bindingResult.getFieldError("id")!=null){
                throw new IllegalUserException(INVALID_USER.label());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }
        //rno로 review값을 조회해 form값과 검증(bno,id 일치하는지)
        if(!isEffectiveFormValue(
                reviewService.getReview(reviewDeleteForm.getRno()),reviewDeleteForm)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }
        //로그인 유저가 맞는지 검증
        if(!validateLoginedUser(reviewDeleteForm.getId(),request)) {
            throw new IllegalUserException(INVALID_USER.label());
        }


        /*
        core
        */
        //rno=={rno} or prno=={rno} 인 경우 삭제
        reviewService.deleteReview(reviewDeleteForm.getRno());
        return ResponseEntity.ok("delete success");
    }//deleteReview



    /**
     * bno->bnoDto
     */
    private BnoDto getBnoDto(Long bno) {
        PageRequest pageRequest = PageRequest.builder().build();
        return BnoDto.builder()
                .bno(bno)
                .page(pageRequest.getPage())
                .size(pageRequest.getSize())
                .build();
    }

    /**
     * reviewForm -> reviewVO
     */
    private ReviewVO getReviewVO(ReviewForm reviewForm) {
        return ReviewVO
                .builder()
                .rno(reviewForm.getRno())
                .bno(reviewForm.getBno())
                .id(reviewForm.getId())
                .content(reviewForm.getContent())
                .build();
    }

    /**
     * 리뷰작성(부모댓글or자식답글)
     */
    private void writeReview(ReviewSaveForm reviewSaveForm, ReviewVO reviewVO) {
        //rno값이 없으면 부모 댓글
        if(reviewSaveForm.getRno()==null){
            //자신의 rno를 prno에 등록
            reviewService.writeParentReview(reviewVO);
        }
        //rno값이 있으면 자식 답글
        else{
            //부모의 rno를 prno에 등록
            reviewVO.setPrno(reviewVO.getRno());
            reviewService.writeChildReview(reviewVO);
        }
    }//writeReview
}
