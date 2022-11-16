package com.example.common.validator;

import com.example.book.vo.BookSearchVO;
import com.example.common.paging.PageRequest;
import com.example.review.controller.form.ReviewForm;
import com.example.review.vo.ReviewVO;
import com.example.user.controller.form.UserLoginForm;
import com.example.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.common.UtilMethod.getUser;

@Slf4j
public class BookshopValidator {

    /*
    Bookshop에서 사용하는 검증 메서드 모음
     */

    /*
     * pageRequest 검증
     */
    public static PageRequest pageRequestResolver(BookSearchVO bookSearchVO, BindingResult bindingResult) {
        //넘어온 pageRequest를 검증(@min,@max)해 오류가 있으면 기본값(page=1,size=9) 설정 ,
        //오류가 없으면 그대로 반환
        PageRequest pageRequest;
        if(bindingResult.hasErrors()){
            //단 페이지(pageRequest.getPage())값만 정확히 넘어온 경우,
            //그 값은 사이즈의 기본값과 함께 사용
            if(bindingResult.getFieldError("page")==null){
                pageRequest = PageRequest.builder().page(bookSearchVO.getPage()).build();
            }else{
                pageRequest = PageRequest.builder().build();
            }
        } else{ //오류가 없으면 그대로 반환
            pageRequest = PageRequest
                    .builder()
                    .page(bookSearchVO.getPage())
                    .size(bookSearchVO.getSize())
                    .build();
        }
        return pageRequest;
    }

    public static PageRequest pageRequestResolver(PageRequest pageRequest, BindingResult bindingResult) {
        //넘어온 pageRequest를 검증(@min,@max)해 오류가 있으면 기본값(page=1,size=9) 설정 ,
        //오류가 없으면 그대로 반환
        if(bindingResult.hasErrors()){
            //단 페이지(pageRequest.getPage())값만 정확히 넘어온 경우,
            //그 값은 사이즈의 기본값과 함께 사용
            if(bindingResult.getFieldError("page")==null){
                pageRequest = PageRequest.builder().page(pageRequest.getPage()).build();
            }else{
                pageRequest = PageRequest.builder().build();
            }
        }
        return pageRequest;
    }

    public static PageRequest pageRequestResolver(String paramPage,String paramSize) {
        PageRequest pageRequest = PageRequest.builder().build();

        //파라미터로 넘어온 page,size가 숫자가 아니면 throw numberformatexception
        //파라미터로 넘어온 page,size가 유효한 값이 아니면 throw
        int page = 0;
        try {
            page = Integer.parseInt(paramPage);
            if(page<1||page>1000) throw new IllegalArgumentException();
            //검증을 통과하면 값 저장
            pageRequest.setPage(page);
        } catch (IllegalArgumentException ignored) {}

        int size = 0;
        try {
            size = Integer.parseInt(paramSize);
            if(size<9||size>100) throw new IllegalArgumentException();
            //검증을 통과하면 값 저장
            pageRequest.setSize(size);
        } catch (IllegalArgumentException ignored) {}

        return pageRequest;
    }
    public static PageRequest pageRequestResolver(PageRequest pageRequest) {
        return pageRequestResolver(String.valueOf(pageRequest.getPage()),String.valueOf(pageRequest.getSize()));
    }

    /*
     * 유저 검증 비밀번호 일치 확인
     */
    public static boolean validateLogin(UserVO user, UserLoginForm userLoginForm){
        return user!=null&&user.getPwd().equals(userLoginForm.getPwd());
    }

    /*
     * 로그인 유저 검증
     */
    public static boolean validateLoginedUser(HttpServletRequest request){
        //세션에 id가 존재하는지
        return getUser(request)!=null && getUser(request).getId()!=null;
    }

    public static boolean validateLoginedUser(String id,HttpServletRequest request){
        //파라미터 id와 세션에 존재하는 id가 일치하는지
        return getUser(request) !=null && id.equals(getUser(request).getId());
    }

    public static boolean validateLoginedUserOrAdmin(String id,HttpServletRequest request){
        //유저or관리자일 경우 검증 통과
        return validateLoginedUser(id,request)||"admin".equals(getUser(request).getId());
    }

    /*
     * 입력폼의 bno,id값 검증(작성한 유저인지 확인)
     */
    public static boolean isEffectiveFormValue(ReviewVO reviewVO, ReviewForm reviewForm){
        //rno로 얻어온 reviewVO와 입력폼으로 얻어온 reviewForm값이 동일한지 검증
        return Objects.equals(reviewVO.getBno(),reviewForm.getBno())&&
                Objects.equals(reviewVO.getId(),reviewForm.getId());
    }

    /*
     * 관리자 검증
     */
    public static boolean validateAdmin(HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        return (userVO==null||"".equals(userVO.getId())||!"admin".equals(userVO.getId()));
    }

}
