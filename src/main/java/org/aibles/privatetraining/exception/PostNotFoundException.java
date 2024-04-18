package org.aibles.privatetraining.exception;

public class PostNotFoundException extends BaseException {

    public PostNotFoundException(String userId) {
        setCode("org.aibles.privatetraining.exception.PostNotFoundException");
        setStatus(404);
        addParams("Post not found", userId);
    }
}
