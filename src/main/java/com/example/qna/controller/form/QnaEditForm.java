package com.example.qna.controller.form;

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
public class QnaEditForm {

    @NotBlank
    private String id;
    @NotBlank(message = "제목을 입력해주세요")
    @Size(min=2,max=20,message = "제목은 2 ~ 20 사이여야 합니다.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
}
