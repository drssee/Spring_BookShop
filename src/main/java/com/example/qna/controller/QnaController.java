package com.example.qna.controller;

import com.example.common.paging.PageRequest;
import com.example.exception.IllegalUserException;
import com.example.qna.controller.form.QnaSaveForm;
import com.example.qna.service.QnaService;
import com.example.qna.vo.QnaVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.example.common.status.RequestStatus.INVALID_USER;
import static com.example.common.validator.BookshopValidator.pageRequestResolver;
import static com.example.common.validator.BookshopValidator.validateLoginedUserOrAdmin;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
@Slf4j
public class QnaController {

    private final QnaService qnaService;

    // *QnaController api*

    //get /qna/qnas <-목록 조회
    //get /qna/{qno} <-단일 조회
    //get /qna/add <-등록폼 이동
    //post /qna/add <-등록
    //get /modify/{qno} <-수정폼 이동
    //post /modify/{qno} <-수정 및 삭제
    //get /qna/search/{id} <-검색byId

    /**
     * 목록 조회
     */
    @GetMapping("/qnas")
    public String qnas(@Validated PageRequest pageRequest, BindingResult bindingResult, Model model){
        /*
        valid
        */
        //pageRequset 체크
        pageRequest = pageRequestResolver(pageRequest,bindingResult);

        /*
        core
        */
        //qnas를 조회해 모델에 담아 반환
        model.addAttribute("pageResponse",qnaService.getQnas(pageRequest));
        return "qna/qnaList";
    }

    /**
     * 단일조회
     */
    @GetMapping("/{qno}")
    public String qna(@PathVariable Long qno){
        return null;
    }

    /**
     * 등록폼 이동
     */
    @GetMapping("/add")
    public String addForm(@Param("page") String page){
        return "qna/qnaRegist";
    }

    /**
     * 등록
     */
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("qna") QnaSaveForm qnaSaveForm,
                      BindingResult bindingResult, HttpServletRequest request,Model model){
        /*
        valid
        */
        //로그인 검증
        if(!validateLoginedUserOrAdmin(qnaSaveForm.getId(),request)){
            throw new IllegalUserException(INVALID_USER.label());
        }

        //qnaform 검증
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return "qna/qnaRegist";
        }

        /*
        core
        */
        //qnaVO 초기화후 db에 저장
        QnaVO qnaVO = new QnaVO(qnaSaveForm);
        qnaService.saveQna(qnaVO);
        return "redirect:/qna/qnas";
    }
}
