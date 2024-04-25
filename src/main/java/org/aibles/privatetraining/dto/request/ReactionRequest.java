package org.aibles.privatetraining.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReactionRequest {

    @NotBlank(message = "Reaction type must not be blank.")
    private String reactionType;

    @NotBlank(message = "User ID must not be blank.")
    private String userId;
}
