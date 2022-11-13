package com.example.orders.controller;

import com.example.orders.controller.form.OrdersForm;
import com.example.orders.service.OrdersService;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrdersController {

    private final OrdersService ordersService;
    private final IamportClient api;
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
        // REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
        this.api = new IamportClient("5006845628855560","BrkGFTinWQ1zKsrKaUEWnLnnDXX5xm850hlZWSGW5AbY9szOFuBA0FmCJJu9pBei47Hc2G4J58cW54u0");
    }

    // *OrdersController api*

    // post /orders/payment <-주문결제, 저장
    // post /orders/verifyIamport/{imp_uid} <-iamport 검증 api

    // get /orders/result <-주문결과 페이지

    @PostMapping("/payment")
    @ResponseBody
    public ResponseEntity<String> payment(@Validated @RequestBody OrdersForm ordersForm
            , BindingResult bindingResult, HttpServletRequest request){
        /*
        valid
        */
        //orderForm 체크
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
//        //로그인 체크
//        if(!validateLoginedUser(ordersForm.getId(),request)){
//            throw new IllegalUserException("요청id와, 로그인id가 일치하지 않습니다.");
//        }

        /*
        core
        */
        //카트에서 온 경우는 재고 변경 x
        //상품상세에서 온 경우는 재고 변경 o

        //OrdersVO초기화
        OrdersVO ordersVO = new OrdersVO(ordersForm);
        //OrdersBookVOList 초기화
        List<OrdersBookVO> ordersBookVOList = new ArrayList<>();
        ordersForm.getOrdersBookForms().forEach(ordersBookForm -> {
            OrdersBookVO ordersBookVO = new OrdersBookVO(ordersBookForm);
            ordersBookVOList.add(ordersBookVO);
        });
        //상품 구매
        ordersService.buyBookFromCart(ordersVO,ordersBookVOList);
        return ResponseEntity.ok("payment success");
    }


    /**
     * iamport 검증 api
     */
    @PostMapping("/verifyIamport/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> paymentByImpUid(
            Model model
            , Locale locale
            , HttpSession session
            , @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
    {
        return api.paymentByImpUid(imp_uid);
    }

//    /**
//     * iamport 결제 취소
//     */
//    @PostMapping("/deleteIamport")
//    @ResponseBody

    /**
     * 주문결과 페이지
     */
    @GetMapping("/result")
    public String result(){
        return "orders/result";
    }


}
