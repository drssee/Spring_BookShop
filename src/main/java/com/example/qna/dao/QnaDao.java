package com.example.qna.dao;

import com.example.common.paging.PageRequest;
import com.example.qna.vo.QnaVO;

import java.util.List;

public interface QnaDao {

    /**
     * 목록 조회
     */
    List<QnaVO> selectQnas(PageRequest pageRequest);

    /**
     * 전체 목록 카운트 조회
     */
    int selectCnt();

    /**
     * qna 저장
     */
    int insertQna(QnaVO qnaVO);
}
