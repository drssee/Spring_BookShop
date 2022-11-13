package com.example.cart.service;

import com.example.cart.vo.CartVO;

import java.util.List;

public interface CartService {

    /**
     * 카트에 상품 저장
     */
    void saveCart(CartVO cartVO);

    /**
     * 카트 조회(id,bno)
     */
    List<CartVO> getCart(CartVO cartVO);

    /**
     * 카트 조회(id)
     */
    List<CartVO> getCartsById(String id);

    /**
     * 카트 갯수 조회(id)
     */
    int getCartCntById(String id);

    /**
     * 카트 아이템 삭제(cno)
     */
    int removeCartItem(Long cno);
}
