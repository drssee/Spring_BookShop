package com.example.book.service;

import com.example.book.dao.BookDao;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.book.vo.ImageVO;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageResponse;
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
    public BookVO registerBook(BookVO bookVO, CategoryVO categoryVO, List<ImageVO> imageVOs) {

        /*
        book관련 insert
         */
        //book db 에 bookVO를 저장
        bookDao.insertBook(bookVO);
        System.out.println("image를 위한 bno!!!!!!!!!!! = "+bookVO.getBno());
        //book_info db에 bookVO를 저장
        bookDao.insertBook_info(bookVO);
        //book_images db에 bookVO를 저장
        insertBook_images(bookVO, imageVOs);

        /*
        category관련 insert
         */
        //categoryName 검증
        validCategoryName(categoryVO);
        //category 에 bookvo.categoryName이 없다면 저장
        bookDao.insertCategory(categoryVO);
        //category_book에 pk들을 저장
        bookDao.insertCategoryBook(bookVO.getBno(),categoryVO.getCategory_name_id());

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

    /*
    카테고리 이름 검증
     */
    private static void validCategoryName(CategoryVO categoryVO) {
        //categoryVo 검증 1보다 작은 값은 존재하지 않는 카테고리이름
        //현재 존재하지 않는 카테고리 = 기타
        if(categoryVO.getCategory_name_id()<1){
            categoryVO.setCategory_name_id(0);
            categoryVO.setCategory_name("기타");
        }
    }

    /*
    각각의 imageVO에 bno를 입력하고 db에 저장
     */
    private void insertBook_images(BookVO bookVO, List<ImageVO> imageVOs) {
        //imageVOs가 존재할때만
        if(imageVOs !=null&& imageVOs.size()>0){
            for (ImageVO imageVO : imageVOs) {
                //이미지 기억을 위한 부모 bno set
                imageVO.setBno(bookVO.getBno());
                bookDao.insertBook_images(imageVO);
            }
        }
    }

}
