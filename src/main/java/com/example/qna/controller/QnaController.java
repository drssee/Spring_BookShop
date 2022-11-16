package com.example.qna.controller;

import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.exception.IllegalUserException;
import com.example.qna.controller.form.QnaEditForm;
import com.example.qna.controller.form.QnaSaveForm;
import com.example.qna.service.QnaService;
import com.example.qna.vo.QnaVO;
import com.example.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.example.common.UtilMethod.getUser;
import static com.example.common.status.RequestStatus.*;
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
    //get /qna/modify/{qno} <-수정폼 이동
    //post /qna/modify/{qno} <-수정
    //get /qna/delete/{qno} <-삭제
    //get /qna/search/{id} <-검색byId
    //get /qna/answer/{qno} <-관리자 답변폼 이동
    //post /qna/answer <-관리자 답변

    /**
     * 목록 조회
     */
    @GetMapping("/qnas")
    public String qnas(@Validated PageRequest pageRequest, BindingResult bindingResult,
                       HttpServletRequest request, Model model){
        /*
        valid
        */
        //pageRequset 체크
        pageRequest = pageRequestResolver(pageRequest,bindingResult);

        /*
        core
        */
        //글조회 권한을 위한 pqno 리스트를 모델에 저장
        UserVO user = getUser(request);
        if(user!=null){
            List<Long> pqnoList = new ArrayList<>();
            qnaService.getQnasById(user.getId()).forEach(qnaVO -> {
                pqnoList.add(qnaVO.getPqno());
            });
            model.addAttribute("pqnoList",pqnoList);
        }
        //qnas를 조회해 모델에 담아 반환
        model.addAttribute("pageResponse",qnaService.getQnas(pageRequest));
        return "qna/qnaList";
    }

    /**
     * 단일조회
     */
    @GetMapping("/{qno}")
    public String qna(@PathVariable Long qno, @ModelAttribute("page") String page ,HttpServletRequest request, Model model){
        /*
        valid
        */
        //qno 체크
        if(qno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //작성자or운영자만 조회 가능
        if(!validateLoginedUserOrAdmin(qnaService.getQna(qno).getId(),request)){
            //작성자가 아니지만, 자신이 작성한 문의의 답변글은 조회할 수 있어야함
            //즉, 해당글의 pqno가 자신이 작성한글의 pqno와 일치하면 볼수 있어야함
            boolean sw = false;
            List<QnaVO> qnasById = qnaService.getQnasById(getUser(request).getId());
            for (QnaVO qnaVO : qnasById) {
                //pqno가 일치할경우 sw=true
                if(qnaService.getQna(qno).getPqno().equals(qnaVO.getPqno())){
                    sw=true;
                    break;
                }
            }
            //일치하는 경우가 없으면 throw exception
            if(!sw){
                throw new IllegalUserException(INVALID_USER.label());
            }
        }

        /*
        core
        */
        //qno로 qna를 조회 , 조회수+1
        model.addAttribute("qna",qnaService.updateQnaCnt(qnaService.getQna(qno)));
        return "qna/qnaDetail";
    }

    /**
     * 등록폼 이동
     */
    @GetMapping("/add")
    public String addForm(@ModelAttribute("page") String page){
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
        //작성글로 이동
        return "redirect:/qna/"+qnaVO.getQno();
    }

    /**
     * 수정폼 이동
     */
    @GetMapping("/modify/{qno}")
    public String modifyForm(@PathVariable("qno") Long qno, @ModelAttribute("page") String page,
                             HttpServletRequest request, Model model){
        /*
        valid
        */
        //qno체크
        if(qno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        QnaVO qna = qnaService.getQna(qno);
        //로그인 체크, 작성자와 로그인유저가 같은지 또는 관리자일 경우
        if(!validateLoginedUserOrAdmin(qna.getId(),request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        /*
        core
        */
        //qnaVO를 모델에 담아 전달
        model.addAttribute("qna",qna);
        return "qna/qnaModify";
    }


    /**
     * 수정
     */
    @PostMapping("/modify/{qno}")
    public String modify(@PathVariable Long qno, @Param("page") String page,
                         @ModelAttribute("qna")QnaEditForm qnaEditForm,
                         BindingResult bindingResult, HttpServletRequest request, Model model){
        /*
        valid
        */
        //qno체크
        if(qno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //로그인 체크, 작성자와 로그인유저가 같은지 또는 관리자일 경우
        if(!validateLoginedUserOrAdmin(qnaEditForm.getId(),request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        //qnaform 검증
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return "qna/qnaModify";
        }

        /*
        core
        */
        //qnaEditForm 값으로 업데이트
        qnaService.updateQna(qno,qnaEditForm);
        return "redirect:/qna/"+qno+"?page="+page;
    }

    /**
     * qna 삭제
     */
    @GetMapping("/delete/{qno}")
    public String delete(@PathVariable Long qno, @Param("page") String page,
                         HttpServletRequest request){
        /*
        valid
        */
        //qno체크
        if(qno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //로그인 체크, 작성자와 로그인유저가 같은지 또는 관리자일 경우
        if(!validateLoginedUserOrAdmin(qnaService.getQna(qno).getId(),request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        /*
        core
        */
        //qna 삭제
        qnaService.removeQna(qno);
        return "redirect:/qna/qnas?page="+page;
    }

    /**
     * qna 검색(id,pqno)
     */
    @GetMapping("/search/{id}")
    public String search(@PathVariable String id,
                         @Validated PageRequest pageRequest,
                         BindingResult bindingResult, Model model, HttpServletRequest request){
        /*
        valid
        */
        //id 체크
        if(id==null||"".equals(id)){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //pageRequest 체크
        pageRequest = pageRequestResolver(pageRequest, bindingResult);

        /*
        core
        */
        //글조회 권한을 위한 pqno 리스트를 모델에 저장
        UserVO user = getUser(request);
        if(user!=null){
            List<Long> pqnoList = new ArrayList<>();
            qnaService.getQnasById(user.getId()).forEach(qnaVO -> {
                pqnoList.add(qnaVO.getPqno());
            });
            model.addAttribute("pqnoList",pqnoList);
        }

        //검색 결과를 모델에 담아 리턴
        PageResponse<QnaVO> pageResponse = qnaService.getSearchedById(id, pageRequest);

        model.addAttribute("pageResponse",pageResponse);
        return "qna/qnaList";
    }

    /**
     * 관리자 답변폼
     */
    @GetMapping("/answer/{qno}")
    public String answerForm(@PathVariable @ModelAttribute("qno") Long qno, HttpServletRequest request){
        /*
        valid
        */
        //qno 체크
        if(qno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //관리자 체크
        if(!"admin".equals(getUser(request).getId())){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        /*
        core
        */
        //작성 페이지로 이동
        return "qna/qnaAnswer";
    }

    /**
     * 관리자 답변
     */
    @PostMapping("/answer")
    public String answer(@Validated @ModelAttribute("qna") QnaSaveForm qnaSaveForm, BindingResult bindingResult,
                         @Param("qno") Long qno, HttpServletRequest request, Model model){
        /*
        valid
        */
        //qno 체크
        System.out.println("qno!!!!!!!!!!!!!!! = " + qno);
        if(qno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //관리자 체크
        if(!"admin".equals(getUser(request).getId())){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        //qnaform 검증
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return "qna/qnaAnswer";
        }

        /*
        core
        */
        //qnaVO 초기화
        QnaVO qnaVO = new QnaVO(qnaSaveForm);
        qnaService.saveQnaAnswer(qno,qnaVO);
        //작성글로 이동
        return "redirect:/qna/"+qnaVO.getQno();
    }
}
