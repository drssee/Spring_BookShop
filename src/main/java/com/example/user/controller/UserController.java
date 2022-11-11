package com.example.user.controller;

import com.example.book.service.BookService;
import com.example.common.paging.PageRequest;
import com.example.user.controller.form.UserLoginForm;
import com.example.user.service.UserService;
import com.example.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.common.validator.BookshopValidator.validateLogin;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    // *UserController api*

    //get /user/login <-로그인폼
    //post /user/login <-로그인체크

    // get /book/books <-전체(출판된) 목록조회
    // get /book/bs <-베스트셀러 목록조회
    // get /book/books_new <-새로나온책 목록조회
    // get /book/books_toBePublished <-출판예정 목록조회
    // get /book/{bno} <-단일조회
    // get /book/add <-단일등록폼
    // post /book/add <-단일등록
    // get /book/{bno}/edit <-단일수정폼
    // post /book/{bno}/edit <-단일수정
    // post /book/{bno}/delete <-단일삭제
    // get /book/search <-상품검색

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request){
        //이전 페이지 정보를 담은 referer변수를 세션에 저장
        request.getSession().setAttribute("referer",request.getHeader("referer"));
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Validated UserLoginForm userLoginForm , BindingResult bindingResult,
                        HttpServletRequest request, HttpServletResponse response, Model model){
        /*
        valid
        */
        //입력폼 검증
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return "user/login";
        }
        //유저 로그인 체크
        UserVO user = userService.getUser(userLoginForm.getId());
        if(!validateLogin(user,userLoginForm)) {
            log.info("errors={}",bindingResult.getGlobalErrors());
            bindingResult.reject("loginError");
            model.addAttribute("bindingResult",bindingResult);
            return "user/login";
        }

        /*
        core
        */
        //세션에 user 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        //세션에 이전페이지 정보가 있다면 그 페이지로 이동
        if(session.getAttribute("referer")!=null){
            String referer = (String) session.getAttribute("referer");
            session.removeAttribute("referer");
            return "redirect:"+referer;
        }
        //베스트셀러 , 새로나온책 프리뷰를 model에 담아 리턴
        model.addAttribute("book_bs",bookService.getBooks_bs());
        model.addAttribute("book_new",bookService.getBookds_new(PageRequest.builder().page(1).size(5).build()));
        return "index";
    }


}
