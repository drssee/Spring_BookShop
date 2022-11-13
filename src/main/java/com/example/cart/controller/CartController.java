package com.example.cart.controller;

import com.example.book.service.BookService;
import com.example.book.vo.BookVO;
import com.example.cart.controller.Dto.CartBookDto;
import com.example.cart.controller.form.CartForm;
import com.example.cart.service.CartService;
import com.example.cart.vo.CartVO;
import com.example.exception.IllegalUserException;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.example.common.status.RequestStatus.BAD_REQUEST;
import static com.example.common.status.RequestStatus.INVALID_USER;
import static com.example.common.util.UtilMethod.getUser;
import static com.example.common.validator.BookshopValidator.validateLoginedUser;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;
    private final BookService bookService;

    // *CartController api*

    // get /cart/carts <-카트 조회(목록)
    // get /cart/check/{bno} <-카트 중복 체크
    // get /cart/size/{id} <-카트 갯수 체크
    // post /cart/save <-카트 저장
    // delete /cart/{cno} <-카트 삭제

    /**
     * 장바구니 조회(목록)
     */
    @GetMapping("/carts")
    public String carts(HttpServletRequest request, Model model){
        /*
        valid
        */
        //로그인 체크
//        if(!validateLoginedUser(request)){
//            throw new IllegalUserException(INVALID_USER.label());
//        }

        /*
        core
        */
        //모델에 cartBookDto리스트를 담아 반환
        List<CartBookDto> cartBookDtoList = new ArrayList<>();
        //카트 아이템 리스트 조회후
        //테스트용
//        List<CartVO> cartList = cartService.getCartsById(getUser(request).getId());
        List<CartVO> cartList = cartService.getCartsById("user1");
        //각 카트 아이템의 상품pk(bno)로 bookVO를 가져와
        //cartBookDto를 생성,초기화 후 리스트에 담아 모델에 전달
        for (CartVO cartVO : cartList) {
            BookVO bookVO = bookService.getBook(cartVO.getBno());
            cartBookDtoList.add(new CartBookDto(bookVO,cartVO));
        }
        model.addAttribute("cartBooks",cartBookDtoList);
        return "cart/carts";
    }

    /**
     * 카트 중복 체크
     */
    @GetMapping("/check/{bno}")
    @ResponseBody
    public ResponseEntity<Boolean> checkCart(@PathVariable Long bno, HttpServletRequest request){
        /*
        valid
        */
        //로그인 체크
        if(!validateLoginedUser(request)) {
            throw new IllegalUserException(INVALID_USER.label());
        }
        //bno 체크
        if(bno<=0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }

        /*
        core
        */
        //cartVO 초기화
        CartVO cartVO = CartVO
                .builder()
                .id(getUser(request).getId())
                .bno(bno)
                .build();
        //id와 bno 로 카트를 검색한 결과가 0개,null이면 true
        //결과가 존재하면 false
        return ResponseEntity.ok(cartService.getCart(cartVO).size()==0);
    }

    /**
     * 카트 갯수 조회(id)
     */
    @GetMapping("/size/{id}")
    @ResponseBody
    public ResponseEntity<Integer> cartSize(@PathVariable String id,HttpServletRequest request){
        /*
        valid
        */
        //id 검증
        if(!validateLoginedUser(id,request)){
            throw new IllegalUserException(INVALID_USER.label());
        }


        /*
        core
        */
        //id에 해당하는 카트의 갯수를 리턴
        return ResponseEntity.ok(cartService.getCartCntById(getUser(request).getId()));
    }

    /**
     * 카트 저장
     */
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<CartVO> saveCart(@Validated @RequestBody CartForm cartForm, BindingResult bindingResult
            , HttpServletRequest request){
        /*
        valid
        */
        //로그인 체크
        if(!validateLoginedUser(request)) {
            throw new IllegalUserException(INVALID_USER.label());
        }
        //cartForm 체크
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST.label());
        }
        //해당 유저 카트에 저장된 상품 갯수 체크
        if(cartService.getCartCntById(getUser(request).getId())>=10){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"카트엔 최대 10개의 상품만 저장 가능합니다");
        }
        //bookVO 초기화 + 재고체크
        BookVO bookVO = bookService.getBook(cartForm.getBno());
        //고객의 사려는 갯수가 재고보다 많으면 예외 발생
        if(cartForm.getQuantity()>bookVO.getStock()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"재고가 부족합니다.");
        }

        /*
        core
        */
        //cartVO 초기화
        CartVO cartVO = CartVO
                .builder()
                .id(cartForm.getId())
                .bno(cartForm.getBno())
                .quantity(cartForm.getQuantity())
                .build();
        //saveCart
        cartService.saveCart(cartVO);
        return ResponseEntity.ok(cartVO);
    }

    /**
     * 카트 아이템 삭제
     */
    @DeleteMapping("/{cno}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteCartItem(@PathVariable Long cno, HttpServletRequest request){
        /*
        valid
        */
        //로그인 체크
        if(!validateLoginedUser(request)){
            throw new IllegalUserException(INVALID_USER.label());
        }
        //cno 체크
        if(cno<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,BAD_REQUEST.label());
        }

        /*
        core
        */
        //카트 아이템 삭제(cno) 성공true 실패 false
        return ResponseEntity.ok(cartService.removeCartItem(cno)==1);
    }
}
