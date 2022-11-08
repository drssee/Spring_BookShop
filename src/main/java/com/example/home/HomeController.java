package com.example.home;

import com.example.book.service.BookService;
import com.example.common.data.CategoryData;
import com.example.common.paging.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.example.book.validator.BookValidator.pageRequestResolver;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String books(@Validated PageRequest pageRequest , BindingResult bindingResult
            , Model model){
        System.out.println("bookService = " + bookService);
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
        return "index";
    }

    @RequestMapping("/categorys")
    @ResponseBody
    public ResponseEntity<List<String>> categorys(){
        //메인 페이지가 로딩될때 rest api로 카테고리 리스트 자동 초기화
        return ResponseEntity.ok().body(CategoryData.categoryNames);
    }
}
