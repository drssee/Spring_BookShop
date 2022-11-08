package com.example.book.service;

import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.book.vo.ImageVO;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;

import java.util.List;

public interface BookService {
    /**
     * 상품(book)등록
     */
    BookVO registerBook(BookVO bookVO, CategoryVO categoryVO, List<ImageVO> imageVOs);

    /**
     * 상품(book)의 (목록)조회
     */
    PageResponse<BookVO> getBooks(PageRequest pageRequest);

    /**
     * 상품(book)의 (단일)조회
     */
    BookVO getBook(Long bno);

    /**
     * 상품(book)의 수정 및 업데이트
     */
    boolean updateBook(BookVO bookVO);

    /**
     * 상품(book)의 삭제
     */
    void removeBook(Long bno);

}