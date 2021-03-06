package com.hh.enums;

import lombok.Getter;

@Getter
public enum  OrderStatus {
    TO_BE_PAID(0, "待付款"),
    TO_BE_SHIPPED(1, "待发货"),
    TO_BE_RECEIVED(2, "待收货"),
    CANCEL(3, "已取消"),
    FINISH(4, "已完成"),
    REFUND_REQUEST(5, "退款申请"),
    REFUND_SUCCESS(6, "退款成功");

    private Integer code;
    private String msg;

    OrderStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OrderStatus getByCode(Integer code){
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getCode().equals(code)){
                return orderStatus;
            }
        }
        return null;
    }
}
