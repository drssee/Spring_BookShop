package com.example.user.service;

import com.example.user.vo.UserLikeVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class UserServiceTest {

    @Autowired(required = false)
    UserService userService;

    String id = "user1";

    @Test
    @DisplayName("유저 조회 테스트")
    public void getUserTest(){
        //유저 조회는 성공해야함
        Assertions.assertNotNull(userService.getUser(id));
    }

    @Test
    @DisplayName("유저 좋아요 목록 체크")
    public void checkLikeCntTest(){
        //given
        //임의의 userLikeVO
        UserLikeVO userLikeVO = UserLikeVO
                .builder()
                .id(id)
                .bno(1001L)
                .build();

        //when,then
        //유저 좋아요 목록 조회는 성공해야함
        Assertions.assertTrue(
                userService.checkLikeCnt(userLikeVO)>=0);
    }

    @Test
    @DisplayName("유저의 좋아요 히스토리 저장")
    public void saveLikeCntTest(){
        //given
        //임의의 userLikeVO
        UserLikeVO userLikeVO = UserLikeVO
                .builder()
                .id(id)
                .bno(1001L)
                .build();

        //when,then
        //좋아요 히스토리 저장은 성공해야함
        Assertions.assertEquals(1, userService.saveLikeCnt(userLikeVO));
    }

//    @Test
//    @DisplayName("회원가입 테스트")
//    public void saveUserTest(){
//        //given
//        //임의의 userVO 초기화
//        UserVO userVO = userService.getUser(id);
//        userVO.setId(id+"2");
//
//        //when
//
//        //then
//    }
}
