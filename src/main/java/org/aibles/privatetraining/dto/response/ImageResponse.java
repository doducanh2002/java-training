package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.entity.ImagePost;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private String imageId;
    private String url;
    private String caption;

    public static ImageResponse from(Image image) {
        ImageResponse response = new ImageResponse();
        response.setImageId(image.getImageId());
        response.setUrl(image.getUrl());
        response.setCaption(image.getCaption());
        return response;
    }
}
