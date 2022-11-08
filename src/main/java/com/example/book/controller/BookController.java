package com.example.book.controller;

import com.example.book.controller.dto.BnoDto;
import com.example.book.vo.ImageVO;
import com.example.book.controller.form.BookEditForm;
import com.example.book.controller.form.BookSaveForm;
import com.example.book.service.BookService;
import com.example.book.vo.BookVO;
import com.example.book.vo.CategoryVO;
import com.example.common.file.FileIO;
import com.example.common.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.example.book.validator.BookValidator.*;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    private final FileIO fileIO;

    ///////////////////////////////////////////코어로직 실패하면 예외 던지기 500에러

    // *BookController api*
    // get /book/{bno} <-단일조회
    // get /book/add <-단일등록폼
    // post /book/add <-단일등록
    // get /book/{bno}/edit <-단일수정폼
    // post /book/{bno}/edit <-단일수정
    // post /book/{bno}/delete <-단일삭제

    //다운로드 경로 추가?

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
                ,"common/alert","/bookshop/book/books?page="+bnoDto.getPage()+"&size="+bnoDto.getSize());
        if (errorUrl != null) return errorUrl;

        /*
        core
         */
        //model로 넘겨줄 book객체를 초기화
        BookVO book = bookService.getBook(bnoDto.getBno());


        //해당 bno의 book을 담아 리턴
        model.addAttribute("book",book);
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
        PageRequest pageRequest = pageRequestResolver(request.getParameter("page"),request.getParameter("size"));
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
        List<ImageVO> imageVOList = getImageVOList(form, request);

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
                ,"common/alert","//bookshop?page="+bnoDto.getPage()+"&size="+bnoDto.getSize());
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
        PageRequest pageRequest = pageRequestResolver(request.getParameter("page"),request.getParameter("size"));
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
                ,"common/alert","/bookshop?page="+bnoDto.getPage()+"&size="+bnoDto.getSize());
        if (errorUrl != null) return errorUrl;

        /*
        core
        */
        //bookVO 삭제
        bookService.removeBook(bnoDto.getBno());
        log.info("도서삭제 성공");
        //삭제에 성공하면 리스트로 리다이렉트
        return "redirect:"+"/book/books?page="+bnoDto.getPage()+"&size="+bnoDto.getSize();
    }

    @GetMapping("/search")
    public String searchBook(){
        log.info("searchBook");
        return null;
    }

    /*
    커버이미지(단일)업로드
     */
    private ImageVO getUploadFileVO_cover(BookSaveForm form, HttpServletRequest request) {
        if(form.getUploadFile()!=null){
            return fileIO.uploadFile(form.getUploadFile(), getFiledDir(request));
        }
        return null;
    }

    /*
    책이미지s(리스트)업로드
    */
    private List<ImageVO> getUploadFileVO_imgs(BookSaveForm form, HttpServletRequest request) {
        if(form.getUploadFiles().size()>0){
            return fileIO.uploadFiles(form.getUploadFiles(), getFiledDir(request));
        }
        return null;
    }

    /*
    registerBook을 위한 List<ImageVO> 생성
     */
    private List<ImageVO> getImageVOList(BookSaveForm form, HttpServletRequest request) {
        //uploadfileVO_cover 초기화
        ImageVO imageVO_cover = getUploadFileVO_cover(form, request);
        //uploadfileVO_imgs 초기화
        List<ImageVO> imageVOs = getUploadFileVO_imgs(form, request);
        //cover가 존재하면 imgs와 add
        if(imageVO_cover!=null){
            Objects.requireNonNull(imageVOs).add(imageVO_cover);
        }
        return imageVOs;
    }

    /*
    cos를 이용해 이미지 저장 디렉토리(경로)를 얻어온다
     */
    private static String getFiledDir(HttpServletRequest request) {
        //파일 업로드 경로를 설정
        //세션으로 부터 현재 어플리케이션 컨텍스트를 얻어온다
        ServletContext context = request.getSession().getServletContext();
        //어플리케이션 컨텍스트 루트 바로 아래 /resources/upload/images라는 경로를 얻어옴
        String filedDir = context.getRealPath("/resources/upload/images");
        //파일 업로드 후 uploadFilevo를 초기화
        log.info("filePath = "+ filedDir);
        return filedDir;
    }
}
