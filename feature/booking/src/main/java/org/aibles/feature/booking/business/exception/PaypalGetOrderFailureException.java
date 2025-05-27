package org.aibles.feature.booking.business.exception;

import org.aibles.business.BaseException;

public class PaypalGetOrderFailureException extends BaseException {
    public PaypalGetOrderFailureException() {
        setStatus(500);
        setCode("org.aibles.business.exception.PaypalGetOrderException");
    }
}