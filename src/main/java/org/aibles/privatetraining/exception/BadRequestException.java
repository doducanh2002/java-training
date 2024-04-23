package org.aibles.privatetraining.exception;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        setStatus(400);
        setCode("BadRequestException");
    }
}
