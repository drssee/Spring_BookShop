package com.example.book.controller;

import com.example.book.controller.form.BookEditForm;
import com.example.book.controller.form.BookSaveForm;
import com.example.book.service.BookService;
import com.example.book.vo.BookSearchVO;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.book.vo.ImageVO;
import com.example.common.file.FileIO;
import com.example.common.paging.PageRequest;
import com.example.exception.IllegalUserException;
import com.example.user.service.UserService;
import com.example.user.vo.UserLikeVO;
import com.example.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.common.status.RequestStatus.BAD_REQUEST;
import static com.example.common.status.RequestStatus.UNAUTHORIZED;
import static com.example.common.validator.BookshopValidator.*;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final FileIO fileIO;

    // *BookController api*

    /*
    조회
     */
    // get /book/books <-전체(출판된) 목록조회
    // get /book/bs <-베스트셀러 목록조회
    // get /book/books_new <-새로나온책 목록조회
    // get /book/books_toBePublished <-출판예정 목록조회
    // get /book/{bno} <-단일조회

    /*
    등록
     */
    // get /book/add <-단일등록폼
    // post /book/add <-단일등록

    /*
    수정
     */
    // get /book/{bno}/edit <-단일수정폼
    // post /book/edit <-단일수정

    /*
    삭제
     */
    // post /book/{bno}/delete <-단일삭제

    /*
    검색
     */
    // get /book/search <-상품검색

    /*
    rest api
     */
    // get /book/like/{bno} <-좋아요 기능


    /**
     * 출판된 목록조회
     */
    @GetMapping("/books")
    public String books(@Validated PageRequest pageRequest , BindingResult bindingResult
            , Model model){
        /*
        valid
         */
        //파라미터로 넘어온 pageRequest(page,size)을 pageRequestResolver에 넘겨 유효한 pageRequest값을 가져온다
        pageRequest = pageRequestResolver(pageRequest, bindingResult);

        /*
        core
         */
        //pageRequest를 이용해 페이징처리용 pageResponse 을 가져온후, 모델에 담아 리턴
        model.addAttribute("pageResponse",bookService.getBooks(pageRequest));
        return "book/books";
    }

    /**
     * 베스트셀러 목록조회
     */
    @GetMapping("/books_bs")
    public String Books_bs(Model model) {
        /*
        core
        */
        //베스트 셀러(상위10)개의 목록을 모델에 담아 리턴
        model.addAttribute("books",bookService.getBooks_bs());
        return "book/books_bs";
    }

    /**
     * 새로나온책(이번달) 목록조회
     */
    @GetMapping("/books_new")
    public String books_new(@Validated PageRequest pageRequest , BindingResult bindingResult
            , Model model) {
        /*
        valid
         */
        //파라미터로 넘어온 pageRequest(page,size)을 pageRequestResolver에 넘겨 유효한 pageRequest값을 가져온다
        pageRequest = pageRequestResolver(pageRequest, bindingResult);

        /*
        core
         */
        //pageRequest를 이용해 페이징처리용 pageResponse 을 가져온후, 모델에 담아 리턴
        model.addAttribute("pageResponse",bookService.getBookds_new(pageRequest));
        return "book/books_new";
    }

    /**
     * 출판 예정인 목록조회
     */
    @GetMapping("/books_toBePublished")
    public String books_toBePublished(@Validated PageRequest pageRequest , BindingResult bindingResult
            , Model model){
        /*
        valid
         */
        //파라미터로 넘어온 pageRequest(page,size)을 pageRequestResolver에 넘겨 유효한 pageRequest값을 가져온다
        pageRequest = pageRequestResolver(pageRequest, bindingResult);

        /*
        core
         */
        //pageRequest를 이용해 페이징처리용 pageResponse 을 가져온후, 모델에 담아 리턴
        model.addAttribute("pageResponse",bookService.getBooks_toBePublished(pageRequest));
        return "book/books_toBePublished";
    }



    /**
     * 단일조회
     */
    @GetMapping("/{bno}")
    public String book(@PathVariable Long bno, PageRequest pageRequest, Model model){
        /*
        validation
         */
        //bno 체크
        if(bno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //pageRequset 체크
        pageRequest=pageRequestResolver(pageRequest);

        /*
        core
         */
        //model로 넘겨줄 book객체를 초기화
        BookVO book = bookService.getBook(bno);
        List<ImageVO> book_images = bookService.getBook_Iimages(book.getBno());
        //해당 bno의 book을 담아 리턴
        model.addAttribute("book",book);
        model.addAttribute("images",book_images);
        model.addAttribute("pageRequest",pageRequest);
        return "book/book";
    }

    /**
     * 단일등록폼
     */
    @GetMapping("/add")
    public String addBookForm(HttpServletRequest request){
        /*
        valid
        */
        //관리자 체크
        if(validateAdmin(request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        /*
        core
        */
        //book등록폼으로 이동
        return "book/addForm";
    }

    /**
     * 단일등록
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") @Validated BookSaveForm bookSaveForm,
                          BindingResult bindingResult,HttpServletRequest request, Model model){
        /*
        valid
         */
        //관리자 체크
        if(validateAdmin(request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        //업로드파일 체크
        if(bookSaveForm.getUploadFile().isEmpty()){
            bindingResult.addError(new FieldError("book","uploadFile","커버 이미지를 업로드해주세요"));
        }
        if(bookSaveForm.getUploadFiles().get(0).isEmpty()||bookSaveForm.getUploadFiles().size()>5){
            bindingResult.addError(new FieldError("book","uploadFiles","상세정보 이미지를 업로드해주세요"));
        }

        //bookSaveForm 검증
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return "book/addForm";
        }

        /*
        core
         */
        //bookVO 초기화
        BookVO bookVO = new BookVO(bookSaveForm);
        //CategoryVO 초기화
        CategoryVO categoryVO = new CategoryVO(bookSaveForm.getCategoryName());
        //imageVOs 초기화
        bookSaveForm.setBno(bookVO.getBno());
        List<ImageVO> imageVOList = fileIO.getImageVOList(bookSaveForm, request);

        //도서 등록
        BookVO regstedBook = bookService.registerBook(bookVO,categoryVO,imageVOList);
        //등록에 성공하면 해당 도서 조회 페이지로 리다이렉트
        return "redirect:/book/"+regstedBook.getBno();
    }

    /**
     * 단일수정폼(수정,삭제)
     */
    @GetMapping("/{bno}/edit")
    public String editBookForm(@PathVariable Long bno,
                               HttpServletRequest request, Model model){
        /*
        valid
        */
        //bno 체크
        if(bno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //관리자 체크
        if(validateAdmin(request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }


        /*
        core
        */
        //넘어온 bno에 해당하는 book정보를 가져와 넘겨준다 <--업데이트 물품 수정 테스트
        model.addAttribute("book",bookService.getBook(bno));
        //book수정폼으로 이동
        return "book/editForm";
    }

    /**
     * 단일수정
     */
    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") @Validated BookEditForm bookEditForm,
                           BindingResult bindingResult,HttpServletRequest request, Model model){
        /*
        valid
        */
        //관리자 체크
        if(validateAdmin(request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        //bno 체크
        if(bookEditForm.getBno()==null||bookEditForm.getBno()<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //업로드파일 체크
        if(bookEditForm.getUploadFile().isEmpty()){
            bindingResult.addError(new FieldError("book","uploadFile","커버 이미지를 업로드해주세요"));
        }
        if(bookEditForm.getUploadFiles().get(0).isEmpty()||bookEditForm.getUploadFiles().size()>5){
            bindingResult.addError(new FieldError("book","uploadFiles","상세정보 이미지를 업로드해주세요"));
        }

        //bookEditForm 검증
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return "book/editForm";
        }

        /*
        core
        */
        //bookVO 초기화
        BookVO bookVO = new BookVO(bookEditForm);
        //imageVOs 초기화
        List<ImageVO> imageVOList = fileIO.getImageVOList(bookEditForm, request);
        //bookVO 업데이트
        bookService.updateAllBookTables(bookVO,imageVOList);
        //수정에 성공하면 해당 도서 조회 페이지로 리다이렉트
        return "redirect:/book/"+bookVO.getBno();
    }

    /**
     * 단일삭제
     */
    @GetMapping("/{bno}/delete")
    public String deleteBook(@PathVariable Long bno,
                             HttpServletRequest request){
        /*
        valid
        */
        //bno 체크
        if(bno<0){
            throw new IllegalStateException(BAD_REQUEST.label());
        }

        //관리자 체크
        if(validateAdmin(request)){
            throw new IllegalUserException(UNAUTHORIZED.label());
        }

        /*
        core
        */
        //bookVO 삭제
        bookService.removeBook(bno);
        //삭제에 성공하면 리스트로 리다이렉트
        return "redirect:/bookshop";
    }

    /**
     * 상품 검색
     */
    @GetMapping("/search")
    public String searchBook(@ModelAttribute("bookSearchVO") @Validated BookSearchVO bookSearchVO,BindingResult bindingResult
            ,Model model){
        /*
        valid
        */
        //검색목록 페이징을 위한 pagerequest 초기화
        PageRequest pageRequest = pageRequestResolver(bookSearchVO,bindingResult);
        bookSearchVO.setPage(pageRequest.getPage());
        bookSearchVO.setSize(pageRequest.getSize());
        /*
        core
        */
        //완성된 검색 조건을 넘긴후 결과를 받아온다
        model.addAttribute("pageResponse",bookService.getSearchedBooks(bookSearchVO));
        return "book/books_sc";
    }

    /**
     * 좋아요 기능
     */
    @GetMapping("/like/{bno}")
    @ResponseBody
    public ResponseEntity<Long> bookLike(@PathVariable Long bno, HttpServletRequest request){
        /*
        valid
        */
        //세션id 체크
        if(!validateLoginedUser(request)){
            throw new IllegalUserException("먼저 로그인을 해주세요.");
        }
        //userVO 초기화 , userLikeVO 초기화
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        UserLikeVO userLikeVO = UserLikeVO
                .builder()
                .id(userVO.getId())
                .bno(bno)
                .build();
        //해당 bno에 이미 좋아요를 눌렀으면
        if(userService.checkLikeCnt(userLikeVO)!=0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 추천을 한 도서입니다.");
        }


        /*
        core
        */
        //해당 bno의 도서를 조회해 like_cnt(좋아요) +1
        BookVO bookVO = bookService.getBook(bno);
        bookVO.setLike_cnt(bookVO.getLike_cnt()+1);
        bookService.updateBook(bookVO);
        //유저의 좋아요 히스토리 저장
        userService.saveLikeCnt(userLikeVO);
        return ResponseEntity.ok(bookVO.getLike_cnt());
    }

}
