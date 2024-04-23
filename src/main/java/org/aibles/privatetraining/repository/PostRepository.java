package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT p FROM Post p WHERE (:userId IS NULL OR p.userId = :userId) " +
            "AND (:title IS NULL OR p.title LIKE %:title%) " +
            "AND (:content IS NULL OR p.content LIKE %:content%)")
    List<Post> searchPost(String userId, String title, String content);
}