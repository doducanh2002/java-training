package org.aibles.privatetraining.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagePostRequest {

    @NotBlank(message = "Post ID must not be blank.")
    private String postId;

    @NotBlank(message = "Image ID must not be blank.")
    private String imageId;
}
