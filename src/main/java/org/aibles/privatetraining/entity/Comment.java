package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.request.ReactionRequest;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reaction")
public class Comment {

  @Id
  private String id;

  private String postId;

  private String userId;

  private String content;

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

  public static Comment of(CommentRequest commentRequest) {
    Comment comment = new Comment();
    comment.setContent(commentRequest.getContent());
    return comment;
  }
}
