package com.example.orders.controller;

import com.example.book.service.BookService;
import com.example.exception.IllegalUserException;
import com.example.orders.controller.dto.OrdersBookDto;
import com.example.orders.controller.form.OrdersForm;
import com.example.orders.service.OrdersService;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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

import static com.example.common.UtilMethod.getUser;
import static com.example.common.status.OrderStatus.DELIVERY_COMPLETE;
import static com.example.common.status.OrderStatus.PAYMENT_COMPLETE;
import static com.example.common.status.RequestStatus.INVALID_USER;
import static com.example.common.status.RequestStatus.UNAUTHORIZED;
import static com.example.common.validator.BookshopValidator.validateAdmin;
import static com.example.common.validator.BookshopValidator.validateLoginedUser;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrdersController {

    private final OrdersService ordersService;
    private final BookService bookService;

    private final IamportClient api;
    public OrdersController(OrdersService ordersService, BookService bookService) {
        this.ordersService = ordersService;
        this.bookService = bookService;
        // REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
        this.api = new IamportClient("5006845628855560","BrkGFTinWQ1zKsrKaUEWnLnnDXX5xm850hlZWSGW5AbY9szOFuBA0FmCJJu9pBei47Hc2G4J58cW54u0");
    }

    // *OrdersController api*

    // post /orders/payment <-주문결제, 저장
    // post /orders/verifyIamport/{imp_uid} <-iamport 검증 api
    // post /orders/deleteIamport/{imp_uid} <-iamport 환불 api
    // get /orders/result <-주문결과 페이지
    // get /orders/list <-주문목록 페이지
    // get /orders/list/{id} <-주문목록 페이지(관리자 조회)
    // post /orders/delevery/{orders_id} <-배송(관리자 기능)

    /**
     * 주문결제
     */
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
        //로그인 체크
        if(!validateLoginedUser(ordersForm.getId(),request)){
            throw new IllegalUserException("요청id와, 로그인id가 일치하지 않습니다.");
        }

        /*
        core
        */
        //OrdersVO초기화
        OrdersVO ordersVO = new OrdersVO(ordersForm);
        //OrdersBookVOList 초기화
        List<OrdersBookVO> ordersBookVOList = new ArrayList<>();
        ordersForm.getOrdersBookForms().forEach(ordersBookForm -> {
            OrdersBookVO ordersBookVO = new OrdersBookVO(ordersBookForm);
            ordersBookVOList.add(ordersBookVO);
        });
        //상품 구매
        ordersService.buyBook(ordersVO,ordersBookVOList,ordersForm.isFromCart());
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

    /**
     * iamport 결제 취소
     */
    @PostMapping("/deleteIamport/{imp_uid}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteOrders(@PathVariable String imp_uid, @Param("order_id") Long order_id,
                                                HttpServletRequest request){

        //imp_uid 검증
        if(imp_uid==null||"".equals(imp_uid)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "결제번호 검증실패");
        }
        //order_id 값이 넘어왔을때
        if(order_id!=null){
            //orderStatus 검증, 주문완료 상태가 아니면 불일치(관리자제외)
            if(!PAYMENT_COMPLETE.label()
                    .equals(ordersService.getOrders(order_id).getOrder_status())){
                //관리자인지 확인(관리자가 아니면 throw exception)
                if(validateAdmin(request)){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"주문상태 불일치");
                }
            }
            //orders,orders_book db에서 제거
            ordersService.cancelOrders(order_id);
        }

        //iamport 환불기능
        try {
            //결제번호(imp_uid)를 패스변수로 가져와 canceldata를 초기화
            CancelData cancelData = new CancelData(imp_uid,true);
            //iamport 환불메서드
            api.cancelPaymentByImpUid(cancelData);
        } catch (IamportResponseException | IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"결제 취소에 실패했습니다.");
        }

        return ResponseEntity.ok(true);
    }

    /**
     * 주문결과 페이지
     */
    @GetMapping("/result")
    public String result(){
        return "orders/result";
    }

    /**
     * 주문목록 페이지
     */
    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model){
        /*
        valid
        */
        //로그인 체크
        if(!validateLoginedUser(request)){
            throw new IllegalUserException(INVALID_USER.label());
        }

        /*
        core
        */
        //ordersBooks 초기화
        List<OrdersBookDto> ordersBookDtos = ordersService.getOrdersBookDtos(getUser(request).getId());
        //ordersBookVOList에서 bno꺼내서 bookVO 초기화
        ordersBookDtos.forEach(ordersBookDto -> {
            ordersBookDto.getOrdersBookVOList().forEach(ordersBookVO -> {
                ordersBookVO.setBookVO(bookService.getBook(ordersBookVO.getBno()));
            });//내부 foreach
        });//외부 foreach

        //초기화한 ordersBooks 모델에 저장
        model.addAttribute("ordersBooks",ordersBookDtos);
        return "orders/orders";
    }

    /**
     * 주문목록 페이지
     */
    @GetMapping("/list/{id}")
    public String list(@PathVariable String id, HttpServletRequest request, Model model){
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
        //ordersBooks 초기화
        List<OrdersBookDto> ordersBookDtos = ordersService.getOrdersBookDtos(id);
        //ordersBookVOList에서 bno꺼내서 bookVO 초기화
        ordersBookDtos.forEach(ordersBookDto -> {
            ordersBookDto.getOrdersBookVOList().forEach(ordersBookVO -> {
                ordersBookVO.setBookVO(bookService.getBook(ordersBookVO.getBno()));
            });//내부 foreach
        });//외부 foreach

        //초기화한 ordersBooks 모델에 저장
        model.addAttribute("ordersBooks",ordersBookDtos);
        return "orders/orders";
    }

    /**
     * 배송(관리자기능)
     */
    @PostMapping("/delevery/{order_id}")
    @ResponseBody
    public ResponseEntity<Boolean> delevery(@PathVariable Long order_id, HttpServletRequest request){
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
        //주문상태 변경
        OrdersVO orders = ordersService.getOrders(order_id);
        orders.setOrder_status(DELIVERY_COMPLETE.label());
        return ResponseEntity.ok(ordersService.updateOrders(orders)==1);
    }
}
