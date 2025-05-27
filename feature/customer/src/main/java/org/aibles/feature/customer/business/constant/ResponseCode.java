package org.aibles.feature.customer.business.constant;

public enum ResponseCode {

    SUCCESS("success");

    private final String value;

    ResponseCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
