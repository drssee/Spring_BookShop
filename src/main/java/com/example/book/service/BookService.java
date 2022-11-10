package com.example.book.service;

import com.example.book.vo.BookSearchVO;
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
     * 상품(book)의 (단일)조회
     */
    BookVO getBook(Long bno);

    /**
     * 상품(출판된book)의 (목록)조회
     */
    PageResponse<BookVO> getBooks(PageRequest pageRequest);

    /**
     * 상품(베스트셀러) 목록 조회
     */
    List<BookVO> getBooks_bs();
    PageResponse<BookVO> getBooks_bs_paging(PageRequest pageRequest);

    /**
     * 상품(새로나온책) 목록 조회
     */
    PageResponse<BookVO> getBookds_new(PageRequest pageRequest);

    /**
     * 상품(출판예정book)의 (목록)조회
     */
    PageResponse<BookVO> getBooks_toBePublished(PageRequest pageRequest);


    /**
     * 내부 이미지 조회
     */
    List<ImageVO> getBook_Iimages(Long bno);


    /**
     * 상품(book)의 수정 및 업데이트
     */
    boolean updateBook(BookVO bookVO);

    /**
     * 상품(book)의 삭제
     */
    void removeBook(Long bno);

    /**
     *  삼품(이름,카테고리,이름+내용) 검색
     */
    PageResponse<BookVO> getSearchedBooks(BookSearchVO bookSearchVO);

}
