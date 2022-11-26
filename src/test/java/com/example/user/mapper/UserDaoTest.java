package com.example.user.mapper;

import com.example.user.dao.UserDao;
import com.example.user.vo.UserLikeVO;
import com.example.user.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class UserDaoTest {

    @Autowired(required = false)
    UserDao userDao;

    String id = "user1";

    @Test
    @DisplayName("selectOne(유저) 테스트")
    public void selectOneTest() throws Exception {
        //given
        //임의의 존재하는 userId를 초기화

        //when,then
        //유저조회는 성공해야 한다(notnull)
        Assertions.assertNotNull(userDao.selectOne(id));
        //조회한 유저의 id는 "user1"이어야 한다
        assertThat(id).isEqualTo(userDao.selectOne(id).getId());
    }

    @Test
    @DisplayName("selectCntLike(좋아요) 테스트")
    public void selectCntLikeTest(){
        //given
        //임의의 userLikeVO를 초기화
        UserLikeVO userLikeVO = UserLikeVO
                .builder()
                .bno(1001L)
                .id(id)
                .build();

        //when,then
        //좋아요 카운트 조회
        Assertions.assertTrue(userDao.selectCntLike(userLikeVO)>=0);
    }

    @Test
    @DisplayName("insertUserLike(좋아요 insert) 테스트 ")
    public void insertUserLikeTest(){
        //given
        //임의의 userLikeVO를 초기화
        UserLikeVO userLikeVO = UserLikeVO
                .builder()
                .bno(1L)
                .id(id)
                .build();

        //when
        //좋아요 카운트++ 하기전에 prevCnt
        int prevCnt = userDao.selectCntLike(userLikeVO);
        //좋아요 카운트++
        userDao.insertUserLike(userLikeVO);

        //then
        Assertions.assertEquals(
                userDao.selectCntLike(userLikeVO), (prevCnt + 1));
    }

    @Test
    @DisplayName("insertUser(유저 저장) 테스트")
    public void insertUserTest(){
        //given
        //임의의 userVO 초기화
        UserVO userVO = userDao.selectOne(id);
        userVO.setId(id+"2");
        userVO.setUuid(UUID.randomUUID().toString());

        //when,then
        //insert는 성공해야함
        assertThat(1).isEqualTo(userDao.insertUser(userVO));
    }

    @Test
    @DisplayName("updateUser(유저 업데이트) 테스트")
    public void updateUserTest(){
        //given
        //임의의 userVO 초기화
        UserVO userVO = userDao.selectOne(id);

        //when
        //userVO 업데이트
        userVO.setPwd("pwd1234");
        userDao.updateUser(userVO);

        //then
        //업데이트 성공여부 체크
        assertThat("pwd1234")
                .isEqualTo(userDao.selectOne(id).getPwd());
    }
}
