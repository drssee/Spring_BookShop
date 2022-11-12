package com.example.book.controller;

import com.example.book.controller.form.BookEditForm;
import com.example.book.controller.form.BookForm;
import com.example.book.controller.form.BookSaveForm;
import com.example.book.service.BookService;
import com.example.book.vo.BookSearchVO;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.book.vo.ImageVO;
import com.example.common.dto.BnoDto;
import com.example.common.file.FileIO;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageSetable;
import com.example.common.validator.BookshopValidator;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.common.status.RequestStatus.BAD_REQUEST;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final FileIO fileIO;

    ///////////////////////////////////////////코어로직 실패하면 예외 던지기 500에러

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
    // post /book/{bno}/edit <-단일수정

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
    public String book(@ModelAttribute("bnoDto") @Validated BnoDto bnoDto, BindingResult bindingResult
            , Model model){
        /*
        validation
         */
        //bnoDto 검증후 바인딩 에러가 있을시 errorUrl을 리턴
        String errorUrl = validateBnoDto(bnoDto, bindingResult, model
                ,"common/alert","/bookshop");
        if (errorUrl != null) return errorUrl;

        /*
        core
         */
        //model로 넘겨줄 book객체를 초기화
        BookVO book = bookService.getBook(bnoDto.getBno());
        List<ImageVO> book_images = bookService.getBook_Iimages(book.getBno());
        //해당 bno의 book을 담아 리턴
        model.addAttribute("book",book);
        model.addAttribute("images",book_images);
        return "book/book";
    }

    /**
     * 단일등록폼
     */
    @GetMapping("/add")
    public String addBookForm(@ModelAttribute("pageRequest") PageRequest pageRequest,BindingResult bindingResult){
        pageRequest = pageRequestResolver(pageRequest,bindingResult);
        //book등록폼으로 이동
        return "book/addForm";
    }

    /**
     * 단일등록
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") @Validated BookSaveForm form,
                          BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                          HttpServletRequest request){
        /*
        valid
         */
        //pageRequest 검증+유효한값 저장
        PageRequest pageRequest = BookshopValidator.pageRequestResolver(request.getParameter("page"),request.getParameter("size"));
        //form 검증후 바인딩 에러가 있을시 errorUrl을 리턴
        String errorUrl = validateForm(form, bindingResult, model, "book/addForm");
        if (errorUrl != null) return errorUrl;

        /*
        core
         */
        //bookVO 초기화
        BookVO bookVO = new BookVO(form);
        //CategoryVO 초기화
        CategoryVO categoryVO = new CategoryVO(form.getCategoryName());
        //imageVOs 초기화
        List<ImageVO> imageVOList = fileIO.getImageVOList(form, request);

        //도서 등록
        BookVO regstedBook = bookService.registerBook(bookVO,categoryVO,imageVOList);
        log.info("도서등록 성공");
        //성공후
        //등록에 성공하면 해당 도서 조회 페이지로 리다이렉트
        return "redirect:/book/"+regstedBook.getBno()+"?page="+pageRequest.getPage()+"&size="+pageRequest.getSize();
    }




    /**
     * 단일수정폼(수정,삭제)
     */
    @GetMapping("/{bno}/edit")
    public String editBookForm(@ModelAttribute("bnoDto") @Validated BnoDto bnoDto
            ,BindingResult bindingResult,Model model){
        /*
        valid
        */
        //bnoDto 검증후 바인딩 에러가 있을시 errorUrl을 리턴
        String errorUrl = validateBnoDto(bnoDto, bindingResult, model
                ,"common/alert","/bookshop");
        if (errorUrl != null) return errorUrl;


        /*
        core
        */
        //넘어온 bno에 해당하는 book정보를 가져와 넘겨준다 <--업데이트 물품 수정 테스트
        model.addAttribute("book",bookService.getBook(bnoDto.getBno()));
        //book수정폼으로 이동
        return "book/editForm";
    }

    /**
     * 단일수정
     */
    @PostMapping("/{bno}/edit")
    public String editBook(@ModelAttribute("book") @Validated BookEditForm form,
                           BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes,
                           HttpServletRequest request){
        /*
        valid
        */
        //pageRequest 검증+유효한값 저장
        PageRequest pageRequest = BookshopValidator.pageRequestResolver(request.getParameter("page"),request.getParameter("size"));
        //BookUpdateForm 검증후 바인딩 에러가 있을시 errorUrl을 리턴
        String errorUrl = validateForm(form, bindingResult, model, "book/editForm");
        if (errorUrl != null) return errorUrl;

        /*
        core
        */
        //bookVO 초기화
        BookVO bookVO = new BookVO(form);
        //bookVO 업데이트
        bookService.updateBook(bookVO);
        log.info("도서수정 성공");
        //성공후
        //수정에 성공하면 해당 도서 조회 페이지로 리다이렉트
        return "redirect:/book/"+bookVO.getBno()+"?page="+pageRequest.getPage()+"&size="+pageRequest.getSize();
    }

    /**
     * 단일삭제
     */
    @GetMapping("/{bno}/delete")
    public String deleteBook(@ModelAttribute("bnoDto") @Validated BnoDto bnoDto
            ,BindingResult bindingResult,Model model){
        /*
        valid
        */
        //bnoDto 검증후 바인딩 에러가 있을시 errorUrl을 리턴
        String errorUrl = validateBnoDto(bnoDto, bindingResult, model
                ,"common/alert","/bookshop");
        if (errorUrl != null) return errorUrl;

        /*
        core
        */
        //bookVO 삭제
        bookService.removeBook(bnoDto.getBno());
        log.info("도서삭제 성공");
        //삭제에 성공하면 리스트로 리다이렉트
        return "redirect:"+"/bookshop";
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
        if(!BookshopValidator.validateLoginedUser(request)){
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

    /*
     * 파라미터로 넘어온 form,dto에 검증된 page,size 셋팅
     */
    private void pageSet(PageSetable pageSetable, PageRequest pageRequest){
        pageSetable.setPage(pageRequest.getPage());
        pageSetable.setSize(pageRequest.getSize());
    }


    /*
    bookcontroller에 의존적인 검증메서드 모음
     */

    /*
     * pageRequest 검증
     */
    public PageRequest pageRequestResolver(BookSearchVO bookSearchVO, BindingResult bindingResult) {
        //넘어온 pageRequest를 검증(@min,@max)해 오류가 있으면 기본값(page=1,size=9) 설정 ,
        //오류가 없으면 그대로 반환
        PageRequest pageRequest;
        if(bindingResult.hasErrors()){
            //단 페이지(pageRequest.getPage())값만 정확히 넘어온 경우,
            //그 값은 사이즈의 기본값과 함께 사용
            if(bindingResult.getFieldError("page")==null){
                pageRequest = PageRequest.builder().page(bookSearchVO.getPage()).build();
            }else{
                pageRequest = PageRequest.builder().build();
            }
        } else{ //오류가 없으면 그대로 반환
            pageRequest = PageRequest
                    .builder()
                    .page(bookSearchVO.getPage())
                    .size(bookSearchVO.getSize())
                    .build();
        }
        return pageRequest;
    }

    private PageRequest pageRequestResolver(PageRequest pageRequest, BindingResult bindingResult) {
        //넘어온 pageRequest를 검증(@min,@max)해 오류가 있으면 기본값(page=1,size=9) 설정 ,
        //오류가 없으면 그대로 반환
        if(bindingResult.hasErrors()){
            //단 페이지(pageRequest.getPage())값만 정확히 넘어온 경우,
            //그 값은 사이즈의 기본값과 함께 사용
            if(bindingResult.getFieldError("page")==null){
                pageRequest = PageRequest.builder().page(pageRequest.getPage()).build();
            }else{
                pageRequest = PageRequest.builder().build();
            }
        }
        return pageRequest;
    }

    /*
     * 파라미터로 넘어온 form을 검증 후
     * 바인딩에러가 있으면 errUrl 경로문자 리턴
     * 에러가 없으면 null 리턴
     */
    private String validateForm(BookForm form, BindingResult bindingResult,
                                      Model model, String errUrl) {
        //입력된 form에 오류가 있을경우
        if(bindingResult.hasErrors()){
            log.error("form has error");
            log.info("error={}",bindingResult.getAllErrors());
            //bindingResult와 함께 다시 입력폼으로 이동
            model.addAttribute("bindingResult", bindingResult);
            return errUrl;
        }
        return null;
    }

    /*
     * 파라미터로 넘어온 bnoDto를 검증 후
     * 바인딩에러가 있으면 errUrl 경로문자 리턴
     * 에러가 없으면 null 리턴
     */
    private String validateBnoDto(BnoDto bnoDto, BindingResult bindingResult, Model model, String errUrl,String redUrl) {
        //파라미터로 넘어온 bnoDto에서 현재 페이징 정보(page,size)를 pageRequestResolver에 넘겨
        // 유효한 pageRequest값을 가져온다
        PageRequest pageRequest = pageRequestResolver(
                PageRequest.builder().page(bnoDto.getPage()).size(bnoDto.getSize()).build(),
                bindingResult);
        pageSet(bnoDto,pageRequest);
        //파라미터bno 값 범위 검증 (1~20000)
        if(bindingResult.hasFieldErrors("bno")){
            log.error("bno has error");
            //잘못된 값이 바인딩되면 addFlashAttribute에 오류메시지,리다이렉트url 저장
            // alert.jsp -> book/books?page={page}&size={size}
            setAlertAttributes(model, redUrl);
            return errUrl;
        }
        return null;
    }

    /*
     * bindingResult에 오류가 있을때 redirectAttribute에 msg와 url을 지정
     */
    private void setAlertAttributes(Model model, String url) {
        model.addAttribute("msg",
                URLEncoder.encode(BAD_REQUEST.label(), StandardCharsets.UTF_8));
        model.addAttribute("url",
                url);
    }
}
