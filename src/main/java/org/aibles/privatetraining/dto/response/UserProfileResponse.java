package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.entity.Post;
import org.aibles.privatetraining.entity.Role;
import org.aibles.privatetraining.entity.UserProfile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private String userId;
    private String username;
    private String email;
    private String password;
    private Role role;

    public static UserProfileResponse from(UserProfile user) {
        UserProfileResponse response = new UserProfileResponse();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setRole(user.getRoles());

        return response;
    }
}
