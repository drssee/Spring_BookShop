package com.example.orders.vo;

import com.example.book.vo.BookVO;
import com.example.orders.controller.form.OrdersBookForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersBookVO {
    private Long order_id; //orders pk
    private Long bno; //book pk
    private int order_price; //주문가격
    private int order_quantity; //주문수량
    private BookVO bookVO;

    public OrdersBookVO(OrdersBookForm ordersBookForm){
        this.bno=ordersBookForm.getBno();
        this.order_price=ordersBookForm.getOrder_price();
        this.order_quantity=ordersBookForm.getOrder_quantity();
    }
}
