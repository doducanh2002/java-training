package org.aibles.privatetraining.exception;

public class InvalidOTPException extends RuntimeException {

    public InvalidOTPException() {
        super("Invalid OTP");
    }

}
