package org.aibles.privatetraining.exception;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String userId) {
        setCode("org.aibles.privatetraining.exception.UserNotFoundException");
        setStatus(404);
        addParams("User not found", userId);
    }
}
