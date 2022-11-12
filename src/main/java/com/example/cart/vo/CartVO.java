package com.example.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {

    private Long cno; //pk
    private String id; //유저id
    private Long bno; //book pk
    private int quantity; //유저가 선택한 구매갯수
}
