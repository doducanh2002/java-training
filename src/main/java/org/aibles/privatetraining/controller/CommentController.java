package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.response.CommentResponse;
import org.aibles.privatetraining.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.aibles.privatetraining.util.SecurityService.getUserId;

@RestController
@Slf4j
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{post_id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(@Validated @PathVariable("post_id") String postId, @RequestBody CommentRequest request) {
        log.info("(createComment) Request: {}, postId: {}", request, postId);
        return commentService.createComment(getUserId(), postId, request);
    }

    @DeleteMapping("/{post_id}/comments/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable("post_id") String postId, @PathVariable("comment_id") String commentId) {
        commentService.deleteComment(postId, commentId);
        return "Delete comment successfully";
    }

    @GetMapping("/{post_id}/comments/{comment_id}")
    public CommentResponse getCommentById(@PathVariable("post_id") String postId, @PathVariable("comment_id") String commentId) {
        log.info("(getCommentById) ID: {}", commentId);
        return commentService.getCommentById(postId, commentId);
    }

    @PutMapping("/{post_id}/comments/{comment_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse updateComment(@PathVariable("post_id") String postId, @PathVariable("comment_id") String commentId, @Validated @RequestBody CommentRequest request) {
        log.info("(updateComment) ID: {}, Request: {}", commentId, request);
        return commentService.updateComment(getUserId(), postId, commentId, request);
    }

    @GetMapping("/{post_id}/comments")
    public List<CommentResponse> search(@PathVariable("post_id") String postId) {
        return commentService.getAllComments(postId);
    }
}
