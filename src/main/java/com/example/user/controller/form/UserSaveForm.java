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
public class UserSaveForm implements UserForm  {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min=4,max=15,message = "아이디는 4~15글자 사이여야 합니다.")
    private String id; //유저 id
    @NotBlank(message = "비밀번호를 입력해주세요.")
//    @Size(min=8,max=25,message = "비밀번호는 8~25글자 사이여야 합니다.")
    @Size(min=4,max=15,message = "비밀번호는 8~25글자 사이여야 합니다.")
    private String pwd; //유저 pwd
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2,max=10,message = "이름은 2글자 이상이어야 합니다.")
    private String name; //유저 이름
    private int pay_amount; //현재 유저의 총 구매 금액
    @NotBlank
    @Size(min=3,max=3)
    private String phone1; //전화번호
    @NotBlank
    @Size(min=3,max=4)
    private String phone2;
    @NotBlank
    @Size(min=3,max=4)
    private String phone3;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email; //이메일
    @NotBlank
    private String zipcode; //우편번호
    @NotBlank
    private String addr1; //주소
    @NotBlank
    private String addr2;
    @NotBlank
    private String addr3;
}
