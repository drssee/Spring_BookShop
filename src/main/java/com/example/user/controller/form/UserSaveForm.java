package com.example.user.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveForm {

    @NotBlank
    @Size(min=3,max=10)
    private String id; //유저 id
    @NotBlank
    @Size(min=4,max=15)
    private String pwd; //유저 pwd
    @NotBlank
    @Size(min=2,max=10)
    private String name; //유저 이름
    private int pay_amount; //현재 유저의 총 구매 금액
    private Date regDate; //등록일
}
