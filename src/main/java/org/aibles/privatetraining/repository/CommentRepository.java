package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, String> {

    Optional<Comment> findByIdAndPostId(String commentId, String postId);

    List<Comment> findAllByPostId(String postId);

    boolean existsByIdAndPostId(String commentId, String postId);
}
