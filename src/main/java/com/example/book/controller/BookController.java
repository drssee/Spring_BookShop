package com.example.book.controller;

import com.example.book.controller.form.BookSaveForm;
import com.example.book.controller.pathvariable.BnoDto;
import com.example.book.service.BookService;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.common.dto.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.common.AppUtil.*;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    //api
    // get /book/books?page=?&size=? <-전체조회
    // get /book/{bno} <-단일조회
    // get /book/add <-단일등록폼
    // post /book/add <-단일등록
    // get /book/{bno}/edit <-단일수정폼
    // post /book/{bno}/edit <-단일수정
    // post /book/{bno}/delete <-단일삭제

    //post redirect시 pathvariable 생각

    /**
     * 전체조회
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
     * 단일조회
     */
    @GetMapping("/{bno}")
    //pathVariable로 bnoDTO.getBno()를 받아
    public String book(@ModelAttribute("bnoDto") @Validated BnoDto bnoDto, BindingResult bindingResult
            , Model model){
        /*
        validation
         */
//        //파라미터로 넘어온 bnoDto에서 현재 페이징 정보(page,size)를 pageRequestResolver에 넘겨
//        // 유효한 pageRequest값을 가져온다
//        PageRequest pageRequest = pageRequestResolver(
//                PageRequest.builder().page(bnoDto.getPage()).size(bnoDto.getSize()).build(),
//                bindingResult);
//        pageSet(bnoDto,pageRequest);
//        //파라미터bno 값 범위 검증 (1~20000)
//        if(bindingResult.hasFieldErrors("bno")){
//            log.error("bno has error");
//            //잘못된 값이 바인딩되면 addFlashAttribute에 오류메시지,리다이렉트url 저장
//            // alert.jsp -> book/books?page={page}&size={size}
//            setAlertAttributes(model, getBooksURL(pageRequest));
//            return "common/alert";
//        }
        String errorUrl = validateBnoDto(bnoDto, bindingResult, model);
        if (errorUrl != null) return errorUrl;

        /*
        core
         */
        //해당 bno의 book을 조회 후 모델에 담아 리턴
        model.addAttribute("book",bookService.getBook(bnoDto.getBno()));
        return "book/book";
    }

    /**
     * 단일등록폼
     */
    @GetMapping("/add")
    public String addBookForm(){
        //book등록폼으로 이동
        return "book/addForm";
    }

    /**
     * 단일등록
     */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public String addBook(@ModelAttribute("book") @Validated BookSaveForm form, BindingResult bindingResult,Model model){
        /*
        valid
         */
        //파라미터로 넘어온 form 현재 페이징 정보(page,size)를 pageRequestResolver에 넘겨
        // 유효한 pageRequest값을 가져온다
        PageRequest pageRequest = pageRequestResolver(
                PageRequest.builder().page(form.getPage()).size(form.getSize()).build(),
                bindingResult);
        pageSet(form,pageRequest);
        //입력된 form에 오류가 있을경우
        if(bindingResult.hasErrors()){
            log.error("form has error");
            //bindingResult와 함께 다시 입력폼으로 이동
            model.addAttribute("bindingResult",bindingResult);
            return "book/addForm";
        }

        /*
        core
         */
        //bookVO 초기화
        BookVO bookVO = new BookVO(form);
        //CategoryVO 초기화
        CategoryVO categoryVO = new CategoryVO(form.getCategoryName());
        //도서 등록
        BookVO regstedBook = bookService.registerBook(bookVO,categoryVO);
        log.info("도서등록 성공");
        //등록에 성공하면 해당 도서 조회 페이지로 이동
        return "redirect:/bookshop/book/"+regstedBook.getBno();
    }

    // get /book/{bno}/edit <-단일수정폼
    // post /book/{bno}/edit <-단일수정
    // post /book/{bno}/delete <-단일삭제

    //post redirect시 pathvariable 생각

    /**
     * 단일수정
     */
    @GetMapping("/bno/{bno}/edit")
    public String editBookForm(@ModelAttribute("bnoDto") @Validated BnoDto bnoDto
            ,BindingResult bindingResult,Model model){
        /*
        valid
        */

        String errorUrl = validateBnoDto(bnoDto, bindingResult, model);
        if (errorUrl != null) return errorUrl;


        /*
        core
        */
        //넘어온 bno에 해당하는 book정보를 가져와 넘겨준다 <--업데이트 물품 수정 테스트
        model.addAttribute("book",bookService.getBook(bnoDto.getBno()));
        //book수정폼으로 이동
        return "book/editForm";
    }

    private static String validateBnoDto(BnoDto bnoDto, BindingResult bindingResult, Model model) {
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
            setAlertAttributes(model, getBooksURL(pageRequest));
            return "common/alert";
        }
        return null;
    }

}
