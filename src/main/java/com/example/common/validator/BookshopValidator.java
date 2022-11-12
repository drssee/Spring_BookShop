package com.example.common.validator;

import com.example.common.paging.PageRequest;
import com.example.review.controller.form.ReviewForm;
import com.example.review.vo.ReviewVO;
import com.example.user.controller.form.UserLoginForm;
import com.example.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.common.util.UtilMethod.getUser;

@Slf4j
public class BookshopValidator {

    /*
    Bookshop에서 사용하는 검증 메서드 모음
     */

    /*
     * Param으로 들어온 pageRequest resolver
     */
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

    /*
     * 입력폼의 bno,id값 검증(작성한 유저인지 확인)
     */
    public static boolean isEffectiveFormValue(ReviewVO reviewVO, ReviewForm reviewForm){
        //rno로 얻어온 reviewVO와 입력폼으로 얻어온 reviewForm값이 동일한지 검증
        return Objects.equals(reviewVO.getBno(),reviewForm.getBno())&&
                Objects.equals(reviewVO.getId(),reviewForm.getId());
    }
}
