package org.aibles.privatetraining.exception;

public class UsernameNotFoundException extends BaseException {

    public UsernameNotFoundException(String username) {
        setStatus(404);
        addParams("User not found", username);
    }
}