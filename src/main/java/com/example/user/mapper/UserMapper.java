package com.example.user.mapper;

import com.example.user.vo.UserVO;

public interface UserMapper {

    /**
     * 유저 (단일)조회
     */
    UserVO selectOne(String id);
}
