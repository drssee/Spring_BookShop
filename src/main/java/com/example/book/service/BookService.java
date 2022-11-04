package com.example.book.service;

import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.common.vo.PageRequest;
import com.example.common.vo.PageResponse;

public interface BookService {
    /**
     * 상품(book)등록
     */
    BookVO registBook(BookVO bookVO);

    /**
     * 상품(book)의 가격과, 재고 업데이트
     */
    boolean updateBook(BookVO bookVO);

    /**
     * 상품(book)의 삭제
     */
    void removeBook(Long bno);

    /**
     * 상품(book)의 조회
     */
    PageResponse<BookVO> getBooks(PageRequest pageRequest);

    /**
     * 상품(book)의 구매
     */

}
