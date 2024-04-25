package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.PostRequest;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {
  @Id
  private String postId;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "title")
  private String title;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;


  @PrePersist
  private void prePersistId() {
    this.postId = this.postId== null ? UUID.randomUUID().toString() : this.postId;
  }

  public static Post of(PostRequest request){
    Post post = new Post();
    post.setContent(request.getContent());
    post.setTitle(request.getTitle());
    post.setUserId(request.getUserId());
    return post;
  }
}
