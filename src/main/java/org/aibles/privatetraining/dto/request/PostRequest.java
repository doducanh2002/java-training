package org.aibles.privatetraining.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequest {

    @NotBlank(message = "Content must not be blank.")
    private String content;

    private String title;

    @NotBlank(message = "User ID must not be blank.")
    private String userId;
}
