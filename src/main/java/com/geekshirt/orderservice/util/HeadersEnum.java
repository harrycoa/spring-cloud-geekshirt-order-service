package com.geekshirt.orderservice.util;

public enum HeadersEnum {
    TRACKING_CORRELATION_ID("correlation-id");

    private String value;

    HeadersEnum(String header) {
        this.value = header;
    }

    public String value() {
        return value;
    }
}
