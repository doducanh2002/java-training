package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    UserProfile findByUsername(String username);

    @Query("SELECT u FROM UserProfile u WHERE " +
            "(:username IS NULL OR u.username = :username) " +
            "AND (:email IS NULL OR u.email = :email)")
    List<UserProfile> searchUserProfile(String username, String email);

}
