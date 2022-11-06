package com.example.book.service;

import com.example.book.dao.BookDao;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.common.dto.PageRequest;
import com.example.common.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;

    /**
     * 상품(book)등록
     */
    @Override
    public BookVO registerBook(BookVO bookVO, CategoryVO categoryVO) {
        /*
        isbn api 추가해야함
         */

        //책을 등록하려면
        //1.book db 에 bookVo를 저장
        bookDao.insertBook(bookVO);
        //2.categoryVo 검증 1보다 작은 값은 존재하지 않는 카테고리이름
        //현재 존재하지 않는 카테고리 = 기타
        if(categoryVO.getCategory_name_id()<1){
            categoryVO.setCategory_name_id(0);
            categoryVO.setCategory_name("기타");
        }
        //3.category 에 bookvo.categoryName이 없다면 저장
        bookDao.insertCategory(categoryVO);
        //4.category_book에 pk들을 저장
        bookDao.insertCategoryBook(bookVO.getBno(),categoryVO.getCategory_name_id());
        //5.저장한 bookVo를 반환
        return bookVO;
    }

    /**
     * 상품(book)의 (목록)조회
     */
    @Override
    public PageResponse<BookVO> getBooks(PageRequest pageRequest) {
        //파라미터로 입력받은 pagerequest 정보(page,size)를 이용해서 해당 북 리스트를 얻어온다
        List<BookVO> books = bookDao.selectBooks(pageRequest);
        //얻어온 리스트와 pagerequest를 이용 pageresponse를 초기화해 리턴한다
        return PageResponse.<BookVO>withAll()
                .pageRequest(pageRequest)
                .pageList(books)
                .total(bookDao.bookCnt())
                .build();
    }

    /**
     * 상품(book)의 (단일)조회
     */
    @Override
    public BookVO getBook(Long bno) {
        return bookDao.selectOne(bno);
    }

    /**
     * 상품(book)의 수정 및 업데이트
     */
    @Override
    public boolean updateBook(BookVO bookVO) {
        //업데이트 성공,실패 여부 반환
        return bookDao.updateBook(bookVO)==1;
    }

    /**
     * 상품(book)의 삭제
     */
    @Override
    public void removeBook(Long bno) {
        //책을 삭제하려면
        //1.book Db 삭제
        bookDao.deleteBook(bno);
        //2.bno에 해당하는 category_book Db 삭제
        bookDao.deleteCategory_book(bno);
    }


}
