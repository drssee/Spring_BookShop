package com.example.book.service;

import com.example.book.dao.BookDao;
import com.example.book.vo.BookSearchVO;
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
        //book_info db에 bookVO를 저장
        bookDao.insertBook_info(bookVO);
        //book_images db에 bookVO를 저장
        //이미지 저장 실패 롤백을 위한 try-catch
        try {
            insertBook_images(bookVO, imageVOs);
        } catch (Exception e) {
            throw new RuntimeException("이미지 저장 실패");
        }

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
     * 상품(book)의 (단일)조회
     */
    @Override
    public BookVO getBook(Long bno) {
        return bookDao.selectBook(bno);
    }

    /**
     * 상품(출판된book)의 (목록)조회
     */
    @Override
    public PageResponse<BookVO> getBooks(PageRequest pageRequest) {
        //파라미터로 입력받은 pagerequest 정보(page,size)를 이용해서 해당 북 리스트를 얻어온다
        List<BookVO> books = bookDao.selectBooks(pageRequest);
        //얻어온 리스트와 pagerequest를 이용 pageresponse를 초기화해 리턴한다
        return PageResponse.<BookVO>withAll()
                .pageRequest(pageRequest)
                .pageList(books)
                .total(bookDao.selectBookCnt_before())
                .build();
    }

    /**
     * 상품(베스트셀러) 목록 조회
     */
    @Override
    public List<BookVO> getBooks_bs() {
        return bookDao.selectBooks_bs(PageRequest.builder().build());
    }
    @Override
    public PageResponse<BookVO> getBooks_bs_paging(PageRequest pageRequest) {
        return PageResponse.<BookVO>withAll()
                .pageRequest(pageRequest)
                .total(bookDao.selectBookCnt_before())
                .pageList(bookDao.selectBooks_bs(pageRequest))
                .build();
    }

    /**
     * 상품(새로나온책) 목록 조회
     */
    @Override
    public PageResponse<BookVO> getBookds_new(PageRequest pageRequest) {
        return PageResponse.<BookVO>withAll()
                .pageRequest(pageRequest)
                .total(bookDao.selectBookCnt_new())
                .pageList(bookDao.selectBooks_new(pageRequest))
                .build();
    }

    /**
     * 상품(출판예정book)의 (목록)조회
     */
    @Override
    public PageResponse<BookVO> getBooks_toBePublished(PageRequest pageRequest) {
        return PageResponse.<BookVO>withAll()
                .pageRequest(pageRequest)
                .total(bookDao.selectBookCnt_after())
                .pageList(bookDao.selectBooks_toBePublished(pageRequest))
                .build();
    }

    /**
     * 내부 이미지 조회
     */
    @Override
    public List<ImageVO> getBook_Iimages(Long bno) {
        return bookDao.selectBookImgs(bno);
    }

    /**
     * 상품(book)의 수정 및 업데이트
     */
    @Override
    public void updateBook(BookVO bookVO) {
        bookDao.updateBook(bookVO);
    }

    /**
     * 상품(book,book_info,book_images)의 수정 및 업데이트
     */
    @Override
    public void updateAllBookTables(BookVO bookVO, List<ImageVO> imageVOS) {
        //bookVO,bookInfo,bookImages 업데이트
        bookDao.updateBook(bookVO);
        bookDao.updateBook_info(bookVO);
        //업데이트할 이미지가 있으면
        if(imageVOS!=null){
            //현재 bno에 저장된 이미지 경로를 삭제후 새로운 이미지를 등록
            bookDao.deleteBook_images(bookVO.getBno());
            //book_images db에 bookVO를 저장
            //이미지 저장 실패 롤백을 위한 try-catch
            try {
                insertBook_images(bookVO, imageVOS);
            } catch (Exception e) {
                throw new RuntimeException("이미지 저장 실패");
            }
        }
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


    /**
     * 삼품(이름,카테고리,이름+내용) 검색
     */
    @Override
    public PageResponse<BookVO> getSearchedBooks(BookSearchVO bookSearchVO) {
        PageRequest pageRequest = PageRequest
                .builder()
                .page(bookSearchVO.getPage())
                .size(bookSearchVO.getSize())
                .build();
        return PageResponse.<BookVO>withAll()
                .pageRequest(pageRequest)
                .total(bookDao.selectSearchedCnt(bookSearchVO))
                .pageList(bookDao.searchBooks(bookSearchVO))
                .build();
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
                if(imageVO!=null){
                    imageVO.setBno(bookVO.getBno());
                    bookDao.insertBook_images(imageVO);
                }
            }//for
        }//if
    }//insertBook_images

}
