package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.PostRequest;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

  @Column(name = "parent_id")
  private String parentId;

  @Column(name = "title")
  private String title;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;


  @PrePersist
  private void prePersistId() {
    this.postId = this.postId== null ? UUID.randomUUID().toString() : this.postId;
  }

  public static Post of(String userId, PostRequest request){
    Post post = new Post();
    post.setContent(request.getContent());
    post.setTitle(request.getTitle());
    post.setParentId(request.getParentId());
    post.setUserId(userId);
    return post;
  }

  public static Post of(String userId, String content, String parentId, String title){
    Post post = new Post();
    post.setContent(content);
    post.setTitle(title);
    post.setParentId(parentId);
    post.setUserId(userId);
    post.setCreatedAt(LocalDateTime.now());
    return post;
  }


}
