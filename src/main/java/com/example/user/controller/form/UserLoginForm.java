package com.example.user.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginForm implements UserForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min=4,max=15,message = "아이디는 4~15글자 사이여야 합니다.")
    private String id; //유저 id
    @NotBlank(message = "비밀번호를 입력해주세요.")
//    @Size(min=8,max=25,message = "비밀번호는 8~25글자 사이여야 합니다.")
    @Size(min=4,max=15,message = "비밀번호는 8~25글자 사이여야 합니다.")
    private String pwd; //유저 pwd
    private String chk1; //아이디 저장
    private String chk2; //로그인 상태 유지
}
