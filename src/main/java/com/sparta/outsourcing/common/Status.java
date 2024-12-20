package com.sparta.outsourcing.common;

public enum Status {
    ORDER_COMPLETE("ORDER_COMPLETE"),
    ORDER_ACCEPTED("ORDER_ACCEPTED"),
    COOKING("COOKING"),
    COOKING_COMPLETE("COOKING_COMPLETE"),
    ON_DELIVERY("ON_DELIVERY"),
    DELIVERY_COMPLETE("DELIVERY_COMPLETE"),
    REJECTED("REJECTED");

    Status(String name) {
        this.name = name;
    }
    private final String name;

    public String getName() {
        return name;
    }

    public static Status of(String StatusName) throws IllegalArgumentException {
        for (Status status : values()) {
            if (status.getName().equals(StatusName)) {
                return status;
            }
        }

        throw new IllegalArgumentException("해당하는 이름의 주문상태를 찾을 수 없습니다: " + StatusName);
    }
}