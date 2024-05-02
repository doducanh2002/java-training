package org.aibles.privatetraining.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Image;

import java.util.List;

@Data
@NoArgsConstructor
public class CreatePostRequest {

    @NotBlank(message = "Content must not be blank.")
    private String content;

    private String title;

    private String parentId;

    @NotNull
    private List<@Valid Image> images;
}
