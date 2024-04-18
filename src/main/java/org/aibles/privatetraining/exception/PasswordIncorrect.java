package org.aibles.privatetraining.exception;

public class PasswordIncorrect extends BaseException {

    public PasswordIncorrect(String password) {
        setStatus(400);
        addParams("Password incorrect ",password);
    }
}
