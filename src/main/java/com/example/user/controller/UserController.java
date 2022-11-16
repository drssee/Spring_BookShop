package com.example.user.controller;

import com.example.book.service.BookService;
import com.example.common.paging.PageRequest;
import com.example.exception.IllegalUserException;
import com.example.user.controller.form.UserEditForm;
import com.example.user.controller.form.UserLoginForm;
import com.example.user.controller.form.UserSaveForm;
import com.example.user.service.UserService;
import com.example.user.vo.UserAddrVO;
import com.example.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.common.status.RequestStatus.*;
import static com.example.common.validator.BookshopValidator.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    // *UserController api*

    /*
    유저
     */
    //get /user/login <-로그인폼
    //post /user/login <-로그인
    //get /user/logout <-로그아웃
    //get /user/check/{id} <-아이디체크
    //get /user/join <-회원가입폼
    //post /user/join <-회원가입
    //get /user/myPage <-마이페이지
    //get /user/update/{id} <-업데이트폼
    //post /user/update <-업데이트

    /*
    관리자메뉴
     */
    //get /user/admin/page <-관리자 페이지
    //get /user/admin/search/{id} <-id 존재 확인



    /**
     * 로그인폼
     */
    @GetMapping("/login")
    public String loginForm(HttpServletRequest request){
        //이전 페이지 정보를 담은 referer변수를 세션에 저장
        request.getSession().setAttribute("referer",request.getHeader("referer"));
        return "user/login";
    }

    /**
     * 로그인
     */
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

        //userVO!=null && userVO.pwd==param.pwd  비밀번호검증
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
        //chk2(로그인 기억)이 체크된 경우
        if("2".equals(userLoginForm.getChk2())){
//            //관리자는 제외 , 로그인 기억 쿠키 값에 uuid 저장
//            if(!"admin".equals(userLoginForm.getId())){
//                Cookie cookie = new Cookie("remember_user",
//                        userService.getUser(userLoginForm.getId()).getUuid());
//                cookie.setPath("/");
//                cookie.setMaxAge(60*60*24*7); //7일간 유지
//                response.addCookie(cookie);
//            }
            Cookie cookie = new Cookie("remember_user",
                    userService.getUser(userLoginForm.getId()).getUuid());
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*7); //7일간 유지
            response.addCookie(cookie);
        }

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

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //remember_user 쿠키삭제
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("remember_user")){
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        //세션삭제
        request.getSession().invalidate();
        return "redirect:/";
    }

    /**
     * 아이디 체크
     */
    @GetMapping("/check/{id}")
    @ResponseBody
    public ResponseEntity<UserVO> checkUser(@PathVariable String id){
        /*
        valid
        */
        if(id==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        /*
        core
        */
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * 회원가입폼
     */
    @GetMapping("/join")
    public String joinForm(){
        return "user/join";
    }

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("user") UserSaveForm userSaveForm,
                       BindingResult bindingResult, Model model, HttpServletRequest request){
        /*
        valid
        */
        //아이디 중복 체크
        if(userService.getUser(userSaveForm.getId())!=null){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //usersaveform 검증
        if(bindingResult.hasErrors()){
            if("".equals(userSaveForm.getPhone1())||"".equals(userSaveForm.getPhone2())||"".equals(userSaveForm.getPhone3())){
                bindingResult.addError(new FieldError("user","phone","전화번호를 확인해주세요."));
            }
            if("".equals(userSaveForm.getZipcode())||
                    "".equals(userSaveForm.getAddr1())||"".equals(userSaveForm.getAddr2())||"".equals(userSaveForm.getAddr3())){
                bindingResult.addError(new FieldError("user","addr","주소를 확인해주세요."));
            }
            model.addAttribute("bindingResult",bindingResult);
            return "user/join";
        }

        /*
        core
        */
        //phone,addr 초기화
        String phone = userSaveForm.getPhone1()+"-"+userSaveForm.getPhone2()+"-"+userSaveForm.getPhone3();
        String addr = userSaveForm.getAddr1()+" "+userSaveForm.getAddr2()+" "+userSaveForm.getAddr3();
        //userVO,userAddrVO 초기화 후 유저db에 저장
        UserVO userVO = new UserVO(userSaveForm,phone);
        UserAddrVO userAddrVO = new UserAddrVO(userSaveForm,addr);
        userService.saveUser(userVO,userAddrVO);
        //유저를 모델에 저장
        request.getSession().setAttribute("user",userVO);
        return "redirect:/";
    }

    /**
     * 마이페이지
     */
    @GetMapping("/myPage")
    public String mypage(HttpServletRequest request){
        /*
        valid
        */
        //로그인 체크
        if(!validateLoginedUser(request)){
            throw new IllegalUserException(INVALID_USER.label());
        }


        /*
        core
        */
        return "user/myPage";
    }

    /**
     * 업데이트폼
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable String id, HttpServletRequest request,Model model){
        /*
        valid
        */
        //id 체크
        if(id==null||"".equals(id)||userService.getUser(id)==null){
            throw new IllegalUserException(INVALID_USER.label());
        }

        //로그인 체크
        if(!validateLoginedUser(id,request)){
            throw new IllegalUserException("비로그인 유저입니다.");
        }

        /*
        core
        */
        //모델에 유저정보 담기
        model.addAttribute("user",userService.getUser(id));
        //업데이트폼 이동
        return "user/update";
    }

    /**
     * 업데이트
     */
    @PostMapping("/update")
    public String update(@Validated @ModelAttribute("user") UserEditForm userEditForm,
                         BindingResult bindingResult, HttpServletRequest request, Model model){
        /*
        valid
        */
        //로그인체크
        if(!validateLoginedUser(userEditForm.getId(),request)){
            throw new IllegalUserException(INVALID_USER.label());
        }

        //입력폼체크(메서드로 리팩토링 해야함)
        if(bindingResult.hasErrors()){
            if("".equals(userEditForm.getPhone1())||"".equals(userEditForm.getPhone2())||"".equals(userEditForm.getPhone3())){
                bindingResult.addError(new FieldError("user","phone","전화번호를 확인해주세요."));
            }
            if("".equals(userEditForm.getZipcode())||
                    "".equals(userEditForm.getAddr1())||"".equals(userEditForm.getAddr2())||"".equals(userEditForm.getAddr3())){
                bindingResult.addError(new FieldError("user","addr","주소를 확인해주세요."));
            }
            model.addAttribute("bindingResult",bindingResult);
            return "user/update";
        }

        /*
        core
        */
        //유저정보 업데이트
        //phone,addr 초기화
        String phone = userEditForm.getPhone1()+"-"+userEditForm.getPhone2()+"-"+userEditForm.getPhone3();
        String addr = userEditForm.getAddr1()+" "+userEditForm.getAddr2()+" "+userEditForm.getAddr3();
        //userVO,userAddrVO 초기화 후 업데이트
        UserVO userVO = new UserVO(userEditForm,phone);
        UserAddrVO userAddrVO = new UserAddrVO(userEditForm,addr);
        userService.updateUser(userVO,userAddrVO);
        return "redirect:/user/myPage";
    }

    /**
     * 관리자 페이지
     */
    @GetMapping("/admin/page")
    public String adminPage(HttpServletRequest request) {
        /*
        valid
        */
        //관리자 체크
        if (validateAdmin(request)) {
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        /*
        core
        */
        //관리자 페이지로 이동
        return "user/adminPage";
    }

    /**
     * id존재 확인
     */
    @GetMapping("/admin/search/{id}")
    @ResponseBody
    public ResponseEntity<Boolean> searchId(@PathVariable String id){
        /*
        valid
        */
        //id 체크
        if(id==null||"".equals(id)){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        /*
        core
        */
        //id 존재시 true
        return ResponseEntity.ok(userService.getUser(id)!=null);
    }

}
