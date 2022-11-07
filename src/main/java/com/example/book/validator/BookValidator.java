package com.example.book.validator;

import com.example.book.controller.dto.BnoDto;
import com.example.book.controller.form.BookForm;
import com.example.common.paging.PageRequest;
import com.example.common.paging.PageSetable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.example.common.status.RequestStatus.BAD_REQUEST;

@Slf4j
public class BookValidator {

    //BookController에서 사용하는 검증 메서드 모음

    /*
     * Param으로 들어온 pageRequest resolver
     */
    public static PageRequest pageRequestResolver(String paramPage,String paramSize) {
        PageRequest pageRequest = PageRequest.builder().build();

        //파라미터로 넘어온 page,size가 숫자가 아니면 throw numberformatexception
        //파라미터로 넘어온 page,size가 유효한 값이 아니면 throw
        int page = 0;
        try {
            page = Integer.parseInt(paramPage);
            if(page<1||page>1000) throw new IllegalArgumentException();
            //검증을 통과하면 값 저장
            pageRequest.setPage(page);
        } catch (IllegalArgumentException ignored) {}

        int size = 0;
        try {
            size = Integer.parseInt(paramSize);
            if(size<9||size>100) throw new IllegalArgumentException();
            //검증을 통과하면 값 저장
            pageRequest.setSize(size);
        } catch (IllegalArgumentException ignored) {}

        return pageRequest;
    }

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

    /*
     * 파라미터로 넘어온 form,dto에 검증된 page,size 셋팅
     */
    public static void pageSet(PageSetable pageSetable, PageRequest pageRequest){
        pageSetable.setPage(pageRequest.getPage());
        pageSetable.setSize(pageRequest.getSize());
    }

    /*
     * bindingResult에 오류가 있을때 redirectAttribute에 msg와 url을 지정
     */
    public static void setAlertAttributes(Model model, String url) {
        model.addAttribute("msg",
                URLEncoder.encode(BAD_REQUEST.label(), StandardCharsets.UTF_8));
        model.addAttribute("url",
                url);
    }

    /*
     * 파라미터로 넘어온 bnoDto를 검증 후
     * 바인딩에러가 있으면 errUrl 경로문자 리턴
     * 에러가 없으면 null 리턴
     */
    public static String validateBnoDto(BnoDto bnoDto, BindingResult bindingResult, Model model, String errUrl,String redUrl) {
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
     * 파라미터로 넘어온 form을 검증 후
     * 바인딩에러가 있으면 errUrl 경로문자 리턴
     * 에러가 없으면 null 리턴
     */
    public static String validateForm(BookForm form, BindingResult bindingResult,
                                      Model model, String errUrl) {
        //입력된 form에 오류가 있을경우
        if(bindingResult.hasErrors()){
            log.error("form has error");
            //bindingResult와 함께 다시 입력폼으로 이동
            model.addAttribute("bindingResult", bindingResult);
            return errUrl;
        }
        return null;
    }
}
