package org.aibles.privatetraining.exception;

public class ImagePostNotFoundException extends BaseException {

    public ImagePostNotFoundException(String userId) {
        setCode("org.aibles.privatetraining.exception.ImagePostNotFoundException");
        setStatus(404);
        addParams("ImagePost not found", userId);
    }
}
