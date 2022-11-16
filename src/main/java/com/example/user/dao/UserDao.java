package com.example.user.dao;

import com.example.user.vo.UserAddrVO;
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

    /**
     * 유저 저장
     */
    int insertUser(UserVO userVO);

    /**
     * 유저_주소 저장
     */
    int insertUserAddr(UserAddrVO userAddrVO);

    /**
     * uuid로 userVO 조회
     */
    UserVO selectOneByUUID(String uuid);

    /**
     * 유저 업데이트(user)
     */
    int updateUser(UserVO userVO);

    /**
     * 유저 업데이트(user_addr)
     */
    int updateUserAddr(UserAddrVO userAddrVO);

}
