package com.example.qna.service;

import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.qna.controller.form.QnaEditForm;
import com.example.qna.vo.QnaVO;

import java.util.List;

public interface QnaService {

    /**
     * 목록 조회
     */
    PageResponse<QnaVO> getQnas(PageRequest pageRequest);

    /**
     * 단일 조회
     */
    QnaVO getQna(Long qno);

    /**
     * qna 저장
     */
    int saveQna(QnaVO qnaVO);

    /**
     * qna 업데이트
     */
    int updateQna(Long qno, QnaEditForm qnaEditForm);

    /**
     * qna 조회수 카운트+1
     */
    QnaVO updateQnaCnt(QnaVO qnaVO);

    /**
     * qna 삭제
     */
    int removeQna(Long qno);

    /**
     * qna 검색(id,pqno)
     */
    PageResponse<QnaVO> getSearchedById(String id, PageRequest pageRequest);

    /**
     * qna 목록(id)
     */
    List<QnaVO> getQnasById(String id);

    /**
     * qna 답변 저장
     */
    QnaVO saveQnaAnswer(Long qno,QnaVO qnaVO);
}
