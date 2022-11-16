package com.example.common.interceptor;

import com.example.exception.IllegalUserException;
import com.example.user.dao.UserDao;
import com.example.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class RememberUserInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    UserDao userDao;

    // preHandle() : 컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        //로그인기억 쿠키가 존재하면 , 쿠키의 값으로 유저를 조회해 세션에 저장
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            log.info("쿠키가 존재하지 않습니다.");
            return true;
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("remember_user")&&session.getAttribute("user")==null){
                session.setAttribute("user", userDao.selectOneByUUID(cookie.getValue()));
                //저장된user검증`
                UserVO userVO = (UserVO) session.getAttribute("user");
                if(userVO==null||userVO.getId()==null||"".equals(userVO.getId())){
                    throw new IllegalUserException("remember_user 쿠키의 값이 유효하지 않습니다.");
                }
                log.info("user session 생성");
            }
        }
        // preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
        // 따라서 true로하면 컨트롤러 uri로 가게 됨.
        return true;
    }
}
