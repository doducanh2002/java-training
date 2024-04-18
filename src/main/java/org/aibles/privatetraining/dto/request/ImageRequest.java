package org.aibles.privatetraining.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageRequest {

    @NotBlank(message = "URL must not be blank.")
    private String url;

    private String caption;

}
