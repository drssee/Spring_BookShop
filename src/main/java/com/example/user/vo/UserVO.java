package com.example.user.vo;

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
}
