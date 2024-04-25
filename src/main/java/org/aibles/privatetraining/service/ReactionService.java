package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;

import java.util.List;

public interface ReactionService {

    ReactionResponse createReaction(String postId,ReactionRequest reactionRequest);

    ReactionResponse getReactionById(String postId,String id);

    ReactionResponse updateReaction(String postId,String id, ReactionRequest reactionRequest);

    void deleteReaction(String postId,String reactionId);

    void checkReactionId(String reactionId);

    List<ReactionResponse> searchReaction(String postId);

}
