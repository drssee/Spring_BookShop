package com.example.cart.service;

import com.example.book.dao.BookDao;
import com.example.book.vo.BookVO;
import com.example.cart.dao.CartDao;
import com.example.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService{

    private final CartDao cartDao;
    private final BookDao bookDao;

    /**
     * 카트에 상품 저장
     */
    @Override
    public void saveCart(CartVO cartVO) {
        //bno 해당 bookVO 조회후
        BookVO bookVO = bookDao.selectBook(cartVO.getBno());
        //재고 - 고객요청갯수
        bookVO.setStock(bookVO.getStock()-cartVO.getQuantity());
        //book업데이트
        bookDao.updateBook(bookVO);
        //cart저장
        cartDao.insertCart(cartVO);
    }

    /**
     * 카트 조회(id,bno)
     */
    @Override
    public List<CartVO> getCart(CartVO cartVO) {
        return cartDao.selectCart(cartVO);
    }

    /**
     * 카트 조회(id)
     */
    @Override
    public List<CartVO> getCartsById(String id) {
        return cartDao.selectCartById(id);
    }

    /**
     * 카트 갯수 조회(id)
     */
    @Override
    public int getCartCntById(String id) {
        return cartDao.selectCntById(id);
    }

    /**
     * 카트 아이템 삭제(cno)
     */
    @Override
    public int removeCartItem(Long cno) {
        return cartDao.deleteCartItem(cno);
    }
}
