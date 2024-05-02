package org.aibles.privatetraining.dto.response;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private String postId;
    private String userId;
    private String content;
    private String title;
    private List<ImageResponse> imageUrls; // Danh sách các URL của ảnh
    private LocalDateTime createdAt;

    public static PostResponse from(Post post) {
        PostResponse response = new PostResponse();
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        response.setPostId(post.getPostId());
        response.setUserId(post.getUserId());
        response.setTitle(post.getTitle());
        return response;
    }

    public static PostResponse from(Post post, List<ImageResponse> imageUrls) {
        PostResponse response = new PostResponse();
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        response.setPostId(post.getPostId());
        response.setUserId(post.getUserId());
        response.setTitle(post.getTitle());
        response.setImageUrls(imageUrls);
        return response;
    }
}
