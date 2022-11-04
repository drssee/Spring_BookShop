package com.example.user.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class UserMapperTest {

    @Autowired(required = false)
    UserMapper userMapper;

    String id = "user1";

    @Test
    @DisplayName("selectOne(유저) 테스트")
    public void selectOneTest() throws Exception {
        //given
        //임의의 존재하는 userId를 초기화

        //when,then
        //유저조회는 성공해야 한다(notnull)
        Assertions.assertNotNull(userMapper.selectOne(id));
        //조회한 유저의 id는 "user1"이어야 한다
        assertThat(id).isEqualTo(userMapper.selectOne(id).getId());
    }
}
