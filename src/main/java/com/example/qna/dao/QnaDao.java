package com.example.qna.dao;

import com.example.common.paging.PageRequest;
import com.example.qna.vo.QnaVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QnaDao {

    /**
     * 목록 조회
     */
    List<QnaVO> selectQnas(PageRequest pageRequest);

    /**
     * 단일 조회
     */
    QnaVO selectQna(Long qno);

    /**
     * 전체 목록 카운트 조회
     */
    int selectCnt();

    /**
     * qna 저장
     */
    int insertQna(QnaVO qnaVO);

    /**
     * qna 업데이트
     */
    int updateQna(QnaVO qnaVO);

    /**
     * qna 삭제
     */
    int deleteQna(Long qno);

    /**
     * qna 검색(id,pqno)
     */
    List<QnaVO> selectSearchedQnaById(@Param("id") String id,
                                      @Param("pqnos") List<Long> pqnos,
                                      @Param("skip") int skip,
                                      @Param("size") int size);

    /**
     * qna 검색(id,pqno) 카운트조회
     */
    int selectSearchedCntById(@Param("id") String id,@Param("pqnos") List<Long> pqnos);

    /**
     * qna 목록(id)
     */
    List<QnaVO> selectQnasById(String id);

    /**
     * qna(id) 목록 카운트 조회
     */
    int selectCntById(String id);

    /**
     * qna 답변 저장
     */
    int insertQnaAnswer(QnaVO qnaVO);
}
