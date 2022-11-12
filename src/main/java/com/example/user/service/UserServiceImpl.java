package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.vo.UserLikeVO;
import com.example.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * 유저 (단일)조회
     */
    @Override
    public UserVO getUser(String id) {
        return userDao.selectOne(id);
    }

    /**
     * 유저의 좋아요 목록 체크
     */
    @Override
    public int checkLikeCnt(UserLikeVO userLikeVO) {
        return userDao.selectCntLike(userLikeVO);
    }

    /**
     * 유저의 좋아요 히스토리 db에 저장
     */
    @Override
    public int saveLikeCnt(UserLikeVO userLikeVO) {
        return userDao.insertUserLike(userLikeVO);
    }
}
