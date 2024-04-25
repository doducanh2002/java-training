package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.ReactionRequest;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reaction")
public class Reaction {

  @Id
  private String id;

  private String postId;

  private String userId;

  private String reactionType;

  private Instant createdAt;

  private Instant updatedAt;

  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
  }

  @PreUpdate
  private void preUpdate() {
    this.updatedAt = Instant.now();
  }

  public static Reaction of(ReactionRequest reactionRequest) {
    Reaction reaction = new Reaction();
    reaction.setReactionType(reactionRequest.getReactionType());
    return reaction;
  }
}
