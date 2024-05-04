package org.aibles.privatetraining.exception;

public class EmailNotFoundException extends BaseException {

    public EmailNotFoundException(String email) {
        setCode("org.aibles.privatetraining.exception.EmailNotFoundException");
        setStatus(404);
        addParams("Email not found", email);
    }
}
