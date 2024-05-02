package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.response.CommentResponse;
import org.aibles.privatetraining.entity.Comment;
import org.aibles.privatetraining.entity.Post;
import org.aibles.privatetraining.exception.CommentNotFoundException;
import org.aibles.privatetraining.exception.PostNotFoundException;
import org.aibles.privatetraining.repository.CommentRepository;
import org.aibles.privatetraining.repository.PostRepository;
import org.aibles.privatetraining.service.CommentService;
import org.aibles.privatetraining.service.PostService;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserProfileService userProfileService;
    private final PostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserProfileService userProfileService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userProfileService = userProfileService;
        this.postService = postService;
    }

    @Override
    public CommentResponse createComment(String userId,String postId, CommentRequest commentRequest) {
        log.info("(createComment) postId: {}, commentRequest: {}", postId, commentRequest);
        postService.checkPostId(postId);
        Comment comment = Comment.of(commentRequest);
        comment.setPostId(postId);
        comment.setUserId(userId);
        commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Override
    public CommentResponse getCommentById(String postId, String commentId) {
        log.info("(getCommentById) postId: {}, commentId: {}", postId, commentId);
        postService.checkPostId(postId);
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
        return CommentResponse.from(comment);
    }

    @Override
    public CommentResponse updateComment(String userId, String postId, String commentId, CommentRequest commentRequest) {
        log.info("(updateComment) postId: {}, commentId: {}, commentRequest: {}", postId, commentId, commentRequest);
        postService.checkPostId(postId);
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Override
    public void deleteComment(String postId, String commentId) {
        log.info("(deleteComment) postId: {}, commentId: {}", postId, commentId);
        postService.checkPostId(postId);
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
        commentRepository.delete(comment);
    }

    @Override
    public void checkCommentId(String postId, String commentId) {
        if (!commentRepository.existsByIdAndPostId(commentId, postId)) {
            throw new CommentNotFoundException(commentId);
        }
    }

    @Override
    public List<CommentResponse> getAllComments(String postId) {
        log.info("(getAllComments) postId: {}", postId);
        postService.checkPostId(postId);
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}
