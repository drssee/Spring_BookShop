package com.example.book.dao;

import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.book.vo.ImageVO;
import com.example.common.paging.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {

    /**
     * book
     */
    //book db에 저장
    int insertBook(BookVO bookVO);
    //book_info에 저장
    int insertBook_info(BookVO bookVO);
    //book_images에 저장
    int insertBook_images(ImageVO imageVO);

    //book db (단일)조회
    BookVO selectOne(Long bno);

    //book db (목록)조회
    List<BookVO> selectBooks(PageRequest pageRequest);

    //book db (전체 개수)조회
    int bookCnt();

    //book db 업데이트
    int updateBook(BookVO bookVO);

    //book db 삭제
    int deleteBook(Long bno);


    /**
     * category,category_book
     */
    //category_book db에 저장
    int insertCategoryBook(@Param("bno") Long bno , @Param("category_name_id") int category_name_id);
    //category_book 조회
    int selectCa_Books_categoryNameId(Long bno);
    int deleteCategory_book(Long bno);
    //카테고리 저장
    int insertCategory(CategoryVO categoryVO);
    String selectCategory_name(int category_name_id);
}
