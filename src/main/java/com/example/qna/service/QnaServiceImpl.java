package com.example.qna.service;

import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.qna.controller.form.QnaEditForm;
import com.example.qna.dao.QnaDao;
import com.example.qna.vo.QnaVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class QnaServiceImpl implements QnaService {

    private final QnaDao qnaDao;

    /**
     * 목록 조회
     */
    @Override
    public PageResponse<QnaVO> getQnas(PageRequest pageRequest) {
        return PageResponse.<QnaVO>withAll()
                .pageRequest(pageRequest)
                .pageList(qnaDao.selectQnas(pageRequest))
                .total(qnaDao.selectCnt())
                .build();
    }

    /**
     * 단일 조회
     */
    @Override
    public QnaVO getQna(Long qno) {
        return qnaDao.selectQna(qno);
    }

    /**
     * qna 저장
     */
    @Override
    public int saveQna(QnaVO qnaVO) {
        return qnaDao.insertQna(qnaVO);
    }

    /**
     * qna 업데이트
     */
    @Override
    public int updateQna(Long qno, QnaEditForm qnaEditForm) {
        //qnaVO 조회후 qnaEditForm 값으로 업데이트
        QnaVO qnaVO = qnaDao.selectQna(qno);
        qnaVO.setTitle(qnaEditForm.getTitle());
        qnaVO.setContent(qnaEditForm.getContent());
        return qnaDao.updateQna(qnaVO);
    }

    /**
     * qna 조회수 카운트+1
     */
    @Override
    public QnaVO updateQnaCnt(QnaVO qnaVO) {
        //조회수 카운트+1 후 업데이트
        qnaVO.setCnt(qnaVO.getCnt()+1);
        qnaDao.updateQna(qnaVO);
        return qnaVO;
    }

    /**
     * qna 삭제
     */
    @Override
    public int removeQna(Long qno) {
        return qnaDao.deleteQna(qno);
    }

    /**
     * qna 검색(id,pqno)
     */
    @Override
    public PageResponse<QnaVO> getSearchedById(String id, PageRequest pageRequest) {
        //id로 qna 조회후 pqnoList 초기화
        List<Long> qnas = new ArrayList<>();
        qnaDao.selectQnasById(id).forEach(qnaVO -> {
            qnas.add(qnaVO.getPqno());
        });
        return PageResponse.<QnaVO>withAll()
                .pageRequest(pageRequest)
                .pageList(qnaDao.selectSearchedQnaById(id,qnas,pageRequest.getSkip(),pageRequest.getSize()))
                .total(qnaDao.selectSearchedCntById(id,qnas))
                .build();
    }

    /**
     * qna 목록(id)
     */
    @Override
    public List<QnaVO> getQnasById(String id) {
        return qnaDao.selectQnasById(id);
    }

    /**
     * qna 답변 저장
     */
    @Override
    public QnaVO saveQnaAnswer(Long qno,QnaVO qnaVO) {
        //qno를 pqno에 저장
        qnaVO.setPqno(qno);
        qnaDao.insertQnaAnswer(qnaVO);
        return qnaVO;
    }
}
