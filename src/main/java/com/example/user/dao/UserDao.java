package com.example.user.dao;

import com.example.user.vo.UserVO;

public interface UserDao {

    /**
     * 유저 (단일)조회
     */
    UserVO selectOne(String id);
}
