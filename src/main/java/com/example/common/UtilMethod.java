package com.example.common;

import com.example.user.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

public class UtilMethod {
    /*
    세션에서 userId를 가져오는 메서드
     */
    public static UserVO getUser(HttpServletRequest request) {
        return (UserVO) request.getSession().getAttribute("user");
    }
}
