package com.example.user.service;

import com.example.user.vo.UserLikeVO;
import com.example.user.vo.UserVO;

public interface UserService {

    /**
     * 유저 (단일)조회
     */
    UserVO getUser(String id);


    /**
     * 유저의 좋아요 목록 체크
     */
    int checkLikeCnt(UserLikeVO userLikeVO);

    /**
     * 유저의 좋아요 히스토리 db에 저장
     */
    int saveLikeCnt(UserLikeVO userLikeVO);
}
