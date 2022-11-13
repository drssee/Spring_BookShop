package com.example.cart.dao;

import com.example.cart.vo.CartVO;

import java.util.List;

public interface CartDao {

    /**
     * 카트에 상품 저장
     */
    int insertCart(CartVO cartVO);

    /**
     * 카트 조회(id,bno)
     */
    List<CartVO> selectCart(CartVO cartVO);

    /**
     * 카트 조회(id)
     */
    List<CartVO> selectCartById(String id);

    /**
     * 전체 카트 갯수(id)
     */
    int selectCntById(String id);

    /**
     * 카트 아이템 삭제(cno)
     */
    int deleteCartItem(Long cno);

    /**
     * 카트 아이템 삭제(id)
     */
    void deleteCart(String id);
}
