package com.example.user.vo;

import com.example.user.controller.form.UserSaveForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
    private String id; //유저 id
    private String pwd; //유저 pwd
    private String name; //유저 이름
    private int pay_amount; //현재 유저의 총 구매 금액
    private Date regDate; //등록일
    private String uuid; //쿠키 사용을 위한 uuid
    private String phone;
    private String email;

    public UserVO(UserSaveForm userSaveForm,String phone) {
        this.id=userSaveForm.getId();
        this.pwd=userSaveForm.getPwd();
        this.name=userSaveForm.getName();
        this.phone=phone;
        this.email=userSaveForm.getEmail();
    }
}
