package com.example.user.service;

import com.example.user.vo.UserVO;

public interface UserService {

    /**
     * 유저 (단일)조회
     */
    UserVO getUser(String id);
}
