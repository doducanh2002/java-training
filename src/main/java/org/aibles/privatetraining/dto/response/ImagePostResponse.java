package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.ImagePost;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagePostResponse {
    private String id;
    private String postId;
    private String imageId;

    public static ImagePostResponse from(ImagePost imagePost) {
        ImagePostResponse response = new ImagePostResponse();
        response.setId(imagePost.getId());
        response.setPostId(imagePost.getPostId());
        response.setImageId(imagePost.getImageId());
        return response;
    }
}
