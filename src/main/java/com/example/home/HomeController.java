package com.example.home;

import com.example.book.service.BookService;
import com.example.common.CategoryData;
import com.example.common.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final BookService bookService;

    @GetMapping("/")
    public String books(Model model){
        /*
        valid
         */
        //베스트셀러 , 새로나온책 프리뷰를 위한 page size 셋팅
        PageRequest pageRequest = PageRequest
                .builder()
                .page(1)
                .size(5)
                .build();
        /*
        core
         */
        //베스트셀러 , 새로나온책 프리뷰를 model에 담아 리턴
        model.addAttribute("book_bs",bookService.getBooks_bs());
        model.addAttribute("book_new",bookService.getBookds_new(pageRequest));
        return "index";
    }

    @RequestMapping("/categorys")
    @ResponseBody
    public ResponseEntity<List<String>> categorys(){
        //메인 페이지가 로딩될때 rest api로 카테고리 리스트 자동 초기화
        return ResponseEntity.ok().body(CategoryData.categoryNames);
    }
}
