package com.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
//컨트롤러 테스트를 위한 어노테이션
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Slf4j
public class UserControllerTest {

    //스프링의 servlet-context를 이용하기 위한 설정
    @Autowired(required = false)
    private WebApplicationContext ctx;

    //가짜 Mvc
    private MockMvc mockMvc;

    @Before
    public void setup(){
        //servlet-context를 이용해 가짜 Mvc 초기화
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void loginFormTest() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/user/login"))
                        .andReturn()
                        .getModelAndView()
                        .getViewName()
        );
    }
}
