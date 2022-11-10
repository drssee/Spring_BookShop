package com.example.review.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewVO {
    Long rno;//pk
    Long bno;//book pk
    String id;//user pk
    String content;
    Long prno;//계층구조 위한 prno
    Date reg_date;
    Date update_date;
}
