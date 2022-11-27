package com.example.user.controller;

import com.example.user.controller.form.UserEditForm;
import com.example.user.controller.form.UserLoginForm;
import com.example.user.controller.form.UserSaveForm;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//컨트롤러 테스트를 위한 어노테이션
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Transactional
@Slf4j
public class UserControllerTest {

    //스프링의 servlet-context를 이용하기 위한 설정
    @Autowired(required = false)
    private WebApplicationContext ctx;

    //가짜 Mvc
    private MockMvc mockMvc;

    @Autowired
    UserService userService;

    @BeforeEach
    public void setup(){
        //servlet-context를 이용해 가짜 Mvc 초기화
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    @DisplayName("로그인폼 테스트")
    public void loginFormTest() throws Exception {
        //viewName은 존재해야함
        assertNotNull(
                Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders.get("/user/login"))
                                .andReturn()
                                .getModelAndView())
                                .getViewName()
        );
    }

    @Test
    @DisplayName("로그인 테스트")
    public void loginTest() throws Exception {
        //임의의 UserLoginForm 초기화
        UserLoginForm userLoginForm = UserLoginForm
                .builder()
                .id("user1")
                .pwd("user1")
                .build();

        //성공시 응답코드 200이 리턴되야함
        assertEquals(200,
                mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("id", userLoginForm.getId())
                        .param("pwd", userLoginForm.getPwd())
                ).andReturn().getResponse().getStatus());
    }

    @Test
    @DisplayName("로그아웃 테스트")
    public void logoutTest() throws Exception {
        //성공시 응답코드 200이 리턴되야함
        assertEquals(200,
                mockMvc.perform(MockMvcRequestBuilders.get("/user/logout"))
                        .andReturn().getResponse().getStatus());
    }

    @Test
    @DisplayName("아이디체크 테스트")
    public void checkIdTest() throws Exception {
        //성공시 응답 바디에 content가 존재해야함
        assertTrue(mockMvc.perform(MockMvcRequestBuilders.get("/user/check/user1"))
                .andReturn().getResponse().getContentAsString().length()>0);
    }

    @Test
    @DisplayName("회원가입폼 테스트")
    public void joinFormTest() throws Exception {
        //viewName은 존재해야함
        assertNotNull(
                Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders.get("/user/join"))
                                .andReturn()
                                .getModelAndView())
                        .getViewName()
        );
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void joinTest() throws Exception {
        //임의의 userSaveForm 초기화
        UserSaveForm userSaveForm = UserSaveForm
                .builder()
                .id("user1_test")
                .pwd("user1")
                .name("user1")
                .phone1("010")
                .phone2("111")
                .phone3("2222")
                .email("user1@user1.com")
                .zipcode("zipcode")
                .addr1("addr1")
                .addr2("addr2")
                .addr3("addr3")
                .build();

        //성공시 응답코드 302(리다이렉트)가 리턴되야함
        assertEquals(302,
                mockMvc.perform(MockMvcRequestBuilders.post("/user/join")
                        .param("id",userSaveForm.getId())
                        .param("pwd",userSaveForm.getPwd())
                        .param("name",userSaveForm.getName())
                        .param("phone1",userSaveForm.getPhone1())
                        .param("phone2",userSaveForm.getPhone2())
                        .param("phone3",userSaveForm.getPhone3())
                        .param("email",userSaveForm.getEmail())
                        .param("zipcode",userSaveForm.getZipcode())
                        .param("addr1",userSaveForm.getAddr1())
                        .param("addr2",userSaveForm.getAddr2())
                        .param("addr3",userSaveForm.getAddr3())
                ).andReturn().getResponse().getStatus()
        );
    }

    @Test
    @DisplayName("마이페이지 테스트")
    public void mypageTest() throws Exception {
        //viewName은 존재해야함
        assertNotNull(
                Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders.get("/user/myPage"))
                        .andReturn().getModelAndView()).getViewName()
        );
    }

    @Test
    @DisplayName("업데이트폼 테스트")
    public void updateFormTest() throws Exception {
        //viewName은 존재해야함
        assertNotNull(
                Objects.requireNonNull(
                        mockMvc.perform(MockMvcRequestBuilders.get("/user/update/user1")).andReturn().getModelAndView()
                ).getViewName()
        );
    }

    @Test
    @DisplayName("업데이트 테스트")
    public void updateTest() throws Exception {
        //임의의 userEidtForm 초기화
        UserEditForm userEditForm = UserEditForm
                .builder()
                .id("user2")
                .pwd("user2")
                .name("user2")
                .phone1("010")
                .phone2("111")
                .phone3("2222")
                .email("user2@user2.com")
                .zipcode("zipcode")
                .addr1("addr1")
                .addr2("addr2")
                .addr3("addr3")
                .build();

        //성공시 응답코드 302(리다이렉트)가 리턴되야함
        assertEquals(302,
                mockMvc.perform(MockMvcRequestBuilders.post("/user/update")
                        .sessionAttr("user",userService.getUser("user2"))
                        .param("id",userEditForm.getId())
                        .param("pwd",userEditForm.getPwd())
                        .param("name",userEditForm.getName())
                        .param("phone1",userEditForm.getPhone1())
                        .param("phone2",userEditForm.getPhone2())
                        .param("phone3",userEditForm.getPhone3())
                        .param("email",userEditForm.getEmail())
                        .param("zipcode",userEditForm.getZipcode())
                        .param("addr1",userEditForm.getAddr1())
                        .param("addr2",userEditForm.getAddr2())
                        .param("addr3",userEditForm.getAddr3())
                ).andReturn().getResponse().getStatus()
        );
    }

    @Test
    @DisplayName("관리자페이지 테스트")
    public void adminPageTest() throws Exception {
        //viewName은 존재해야함
        assertNotNull(
                Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders.get("/user/admin/page")
                        .sessionAttr("user",userService.getUser("admin")))
                        .andReturn().getModelAndView()
                ).getViewName()
        );
    }

    @Test
    @DisplayName("id존재확인 테스트")
    public void adminSearchTest() throws Exception {
        //응답 바디에 true가 리턴되야함
        assertEquals("true", mockMvc.perform(MockMvcRequestBuilders.get("/user/admin/search/user2"))
                .andReturn().getResponse().getContentAsString());
    }
}
