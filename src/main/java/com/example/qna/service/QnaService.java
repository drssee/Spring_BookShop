package com.example.qna.service;

import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.qna.vo.QnaVO;

public interface QnaService {

    /**
     * 목록 조회
     */
    PageResponse<QnaVO> getQnas(PageRequest pageRequest);

    /**
     * qna 저장
     */
    int saveQna(QnaVO qnaVO);
}
