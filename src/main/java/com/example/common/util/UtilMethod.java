package com.example.common.util;

import javax.servlet.http.HttpServletRequest;

public class UtilMethod {
    /*
     * 세션에서 userId를 가져오는 메서드
     */
    public static String getId(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
