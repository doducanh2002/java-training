package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.UserProfileRequest;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_profile")
public class UserProfile {
  @Id
  private String userId;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password",nullable = false)
  private String password;

  @Column(name = "role",nullable = false)
  private Role role;

  @PrePersist
  private void prePersistId() {
    this.userId = this.userId== null ? UUID.randomUUID().toString() : this.userId;
  }

  public static UserProfile of(UserProfileRequest request) {
    UserProfile userProfile = new UserProfile();
    userProfile.setEmail(request.getEmail());
    userProfile.setUsername(request.getUsername());
    return userProfile;
  }

}
