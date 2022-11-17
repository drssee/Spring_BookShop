package com.example.orders.service;

import com.example.book.dao.BookDao;
import com.example.book.vo.BookVO;
import com.example.cart.dao.CartDao;
import com.example.orders.controller.dto.OrdersBookDto;
import com.example.orders.dao.OrdersDao;
import com.example.orders.vo.OrdersBookVO;
import com.example.orders.vo.OrdersVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.example.common.status.OrderStatus.PAYMENT_COMPLETE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersDao ordersDao;
    private final CartDao cartDao;
    private final BookDao bookDao;

    /**
     * 상품(book)의 구매
     */
    @Override
    public void buyBook(OrdersVO ordersVO, List<OrdersBookVO> ordersBookVOList
            ,boolean isFromCart) throws ResponseStatusException {

        //장바구니에서 구매한 경우, 장바구니 목록 삭제
        if(isFromCart){
            cartDao.deleteCart(ordersVO.getId());
        }
        //장바구니에서 구매한 경우가 아닐시, 재고 수정
        if(!isFromCart){
            //ordersBookVO의 bno->bookVO 조회한후 재고 업데이트
            ordersBookVOList.forEach(ordersBookVO -> {
                BookVO bookVO = bookDao.selectBook(ordersBookVO.getBno());
                int updatedStock = bookVO.getStock()-ordersBookVO.getOrder_quantity();
                if(updatedStock<0){
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,"재고는 0이상 이어야 합니다");
                }
                bookVO.setStock(updatedStock);
                bookDao.updateBook(bookVO);
            });
        }
        //order_status 수정
        ordersVO.setOrder_status(PAYMENT_COMPLETE.label());
        //db에 주문 저장
        ordersDao.insertOrders(ordersVO);
        ordersBookVOList.forEach(ordersBookVO -> {
            ordersBookVO.setOrder_id(ordersVO.getOrder_id());
            ordersDao.insert_orders_book(ordersBookVO);
        });
    }

    /**
     * 오더 조회
     */
    @Override
    public List<OrdersVO> getOrders(String id) {
        return ordersDao.selectOrders(id);
    }

    /**
     * 오더 조회(id)
     */
    @Override
    public OrdersVO getOrders(Long order_id) {
        return ordersDao.selectOrdersByOrder_id(order_id);
    }

    /**
     * 오더_상품 조회
     */
    @Override
    public List<OrdersBookVO> getOrdersBook(Long order_id) {
        return ordersDao.selectOrdersBook(order_id);
    }

    /**
     * 오더_상품dto 조회
     */
    @Override
    public List<OrdersBookDto> getOrdersBookDtos(String id) {
        List<OrdersBookDto> ordersBooks = new ArrayList<>();
        //id로 orderVOlist를 불러온뒤
        getOrders(id).forEach(ordersVO -> {
            //ordersbookdto 초기화
            OrdersBookDto ordersBookDto = new OrdersBookDto(ordersVO,getOrdersBook(ordersVO.getOrder_id()));
            ordersBooks.add(ordersBookDto);
        });
        return ordersBooks;
    }

    /**
     * 오더 취소
     */
    @Override
    public void cancelOrders(Long order_id) {
        //취소할 상품(들)의 구매 수량을 조회한뒤, 재고+구매수량
        ordersDao.selectOrdersBook(order_id).forEach(ordersBookVO -> {
            BookVO bookVO = bookDao.selectBook(ordersBookVO.getBno());
            bookVO.setStock(bookVO.getStock()+ordersBookVO.getOrder_quantity());
            bookDao.updateBook(bookVO);
        });
        //orders,orders_book db에서 삭제
        ordersDao.deleteOrders(order_id);
    }

    /**
     * 오더 업데이트(주문상태)
     */
    @Override
    public int updateOrders(OrdersVO ordersVO) {
        return ordersDao.updateOrders(ordersVO);
    }
}
