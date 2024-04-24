package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, String> {

    List<Reaction> findAllByPostId(String postId);
}