package org.aibles.privatetraining.exception;

public class ImageNotFoundException extends BaseException {

    public ImageNotFoundException(String userId) {
        setCode("org.aibles.privatetraining.exception.ImageNotFoundException");
        setStatus(404);
        addParams("User not found", userId);
    }
}
