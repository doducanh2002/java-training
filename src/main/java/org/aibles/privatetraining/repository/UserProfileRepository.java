package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
