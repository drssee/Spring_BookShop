package com.example.user.dao;

import com.example.user.vo.UserLikeVO;
import com.example.user.vo.UserVO;

public interface UserDao {

    /**
     * 유저 (단일)조회
     */
    UserVO selectOne(String id);


    /**
     * 유저의 좋아요 목록 체크
     */
    int selectCntLike(UserLikeVO userLikeVO);

    /**
     * 유저의 좋아요 히스토리 db에 저장
     */
    int insertUserLike(UserLikeVO userLikeVO);
}
