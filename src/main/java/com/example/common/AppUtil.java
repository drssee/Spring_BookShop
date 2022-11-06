package com.example.common;

import com.example.book.controller.PageSetable;
import com.example.common.dto.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AppUtil {

    public static final String BAD_REQUEST = "잘못된 요청입니다.";

    /**
     * Param으로 들어온 pageRequest resolver
     */
    public static PageRequest pageRequestResolver(PageRequest pageRequest, BindingResult bindingResult) {
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

    /**
     * 파라미터로 넘어온 form,dto에 검증된 page,size 셋팅
     */
    public static void pageSet(PageSetable pageSetable, PageRequest pageRequest){
        pageSetable.setPage(pageRequest.getPage());
        pageSetable.setSize(pageRequest.getSize());
    }

    /**
     * bindingResult에 오류가 있을때 redirectAttribute에 msg와 url을 지정
     */
    public static void setAlertAttributes(Model model, String url) {
        model.addAttribute("msg",
                URLEncoder.encode(BAD_REQUEST, StandardCharsets.UTF_8));
        model.addAttribute("url",
                url);
    }

    /**
     * 도서목록 페이지 url+쿼리스트링 리턴
     */
    public static String getBooksURL(PageRequest pageRequest){
        return "/bookshop/book/books?page="+pageRequest.getPage()+"&size="+pageRequest.getSize();
    }

    /**
     * 기본 카테고리 설정 리스트(26개)
     */
    public static final List<String> categoryNames = new ArrayList<>();
    static {
        categoryNames.add("건강");
        categoryNames.add("경제경영");
        categoryNames.add("고등학교참고서");
        categoryNames.add("과학");
        categoryNames.add("대학교재");
        categoryNames.add("만화");
        categoryNames.add("사회과학");
        categoryNames.add("소설");
        categoryNames.add("수험서");
        categoryNames.add("어린이");
        categoryNames.add("에세이");
        categoryNames.add("여행");
        categoryNames.add("역사");
        categoryNames.add("예술");
        categoryNames.add("외국어");
        categoryNames.add("요리");
        categoryNames.add("유아");
        categoryNames.add("인문학");
        categoryNames.add("자기계발");
        categoryNames.add("잡지");
        categoryNames.add("종교");
        categoryNames.add("좋은부모");
        categoryNames.add("중학교참고서");
        categoryNames.add("청소년");
        categoryNames.add("초등학교참고서");
        categoryNames.add("컴퓨터");
    }

    //마일리지 설정
//    //못하겠으면 빼기(가격정책config클래스에 상수로 설정하면 될거같긴함)
//    public int getMileage(){
//        return price/10;
//    }
}
