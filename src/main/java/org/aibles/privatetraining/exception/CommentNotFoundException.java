package org.aibles.privatetraining.exception;

public class CommentNotFoundException extends BaseException {

    public CommentNotFoundException(String userId) {
        setCode("org.aibles.privatetraining.exception.CommentNotFoundException");
        setStatus(404);
        addParams("Comment not found", userId);
    }
}
