package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Reaction;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponse {
    private String reactionId;
    private String postId;
    private String userId;
    private String reactionType;
    private Instant createdAt;
    private Instant updatedAt;

    public static ReactionResponse from(Reaction reaction) {
        ReactionResponse response = new ReactionResponse();
        response.setReactionId(reaction.getId());
        response.setPostId(reaction.getPostId());
        response.setUserId(reaction.getUserId());
        response.setReactionType(reaction.getReactionType());
        response.setCreatedAt(reaction.getCreatedAt());
        response.setUpdatedAt(reaction.getUpdatedAt());
        return response;
    }
}
