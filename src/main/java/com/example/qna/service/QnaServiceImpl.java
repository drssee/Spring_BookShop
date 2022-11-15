package com.example.qna.service;

import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
import com.example.qna.dao.QnaDao;
import com.example.qna.vo.QnaVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * qna 저장
     */
    @Override
    public int saveQna(QnaVO qnaVO) {
        return qnaDao.insertQna(qnaVO);
    }
}
