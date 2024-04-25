package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Comment;
import org.aibles.privatetraining.entity.Reaction;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private String commentId;
    private String postId;
    private String userId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public static CommentResponse from(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getId());
        response.setPostId(comment.getPostId());
        response.setUserId(comment.getUserId());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        return response;
    }
}
