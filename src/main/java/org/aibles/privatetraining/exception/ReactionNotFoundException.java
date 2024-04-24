package org.aibles.privatetraining.exception;

public class ReactionNotFoundException extends BaseException {

    public ReactionNotFoundException(String reactionId) {
        setCode("org.aibles.privatetraining.exception.ReactionNotFoundException");
        setStatus(404);
        addParams("Reaction not found", reactionId);
    }
}
