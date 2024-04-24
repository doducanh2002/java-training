package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;

import java.util.List;

public interface ReactionService {

    ReactionResponse createReaction(ReactionRequest reactionRequest);

    ReactionResponse getReactionById(String id);

    ReactionResponse updateReaction(String id, ReactionRequest reactionRequest);

    void deleteReaction(String reactionId);

    void checkReactionId(String reactionId);

    List<ReactionResponse> getAllReactions();

    List<ReactionResponse> searchReaction(String postId);

}
