package com.example.common.status;

public enum OrderStatus {
//    ORDER_COMPLETE("주문완료"),
    PAYMENT_COMPLETE("결제완료"),
    SHIPPING("배송중"),
    DELIVERY_COMPLETE("배송완료");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
