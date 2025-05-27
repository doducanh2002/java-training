package org.aibles.feature.booking.business.exception;

import org.aibles.business.BaseException;

public class PaypalCaptureFailureException extends BaseException {
    public PaypalCaptureFailureException() {
        setStatus(500);
        setCode("org.aibles.business.exception.PaypalCaptureException");
    }
}
