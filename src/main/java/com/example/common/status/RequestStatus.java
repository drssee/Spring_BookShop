package com.example.common.status;

public enum RequestStatus {

    BAD_REQUEST("잘못된 요청입니다"),
    INVALID_USER("잘못된 유저의 요청입니다");

    private final String label;
    RequestStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
