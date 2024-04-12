package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.entity.Post;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private String postId;
    private String userId;
    private String content;
    private String title;
    private Long createdAt;

    public static PostResponse from(Post post) {
        PostResponse response = new PostResponse();
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        response.setPostId(post.getPostId());
        response.setUserId(post.getUserId());
        response.setTitle(post.getTitle());
        return response;
    }
}
