package com.example.qna.vo;

import com.example.qna.controller.form.QnaSaveForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class QnaVO {
    private Long qno;
    private String id;
    private String title;
    private String content;
    private Date reg_date;
    private Date update_date;
    private int cnt;

    public QnaVO(QnaSaveForm qnaSaveForm){
        this.id=qnaSaveForm.getId();
        this.title=qnaSaveForm.getTitle();
        this.content=qnaSaveForm.getContent();
    }

    private QnaVO(){}
}
