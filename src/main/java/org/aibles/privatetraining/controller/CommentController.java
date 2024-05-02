package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.response.CommentResponse;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.aibles.privatetraining.util.SecurityService.getUserId;

@RestController
@Slf4j
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{post_id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createComment(@Validated @PathVariable("post_id") String postId, @RequestBody CommentRequest request) {
        log.info("(createComment) Request: {}, postId: {}", request, postId);
        return Response.of(
                HttpStatus.CREATED.value(),
                commentService.createComment(getUserId(), postId, request));
    }

    @DeleteMapping("/{post_id}/comments/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteComment(@PathVariable("post_id") String postId, @PathVariable("comment_id") String commentId) {
        commentService.deleteComment(postId, commentId);
        return Response.of(
                HttpStatus.OK.value(),
                "Delete comment successfully");
    }

    @GetMapping("/{post_id}/comments/{comment_id}")
    public Response getCommentById(@PathVariable("post_id") String postId, @PathVariable("comment_id") String commentId) {
        log.info("(getCommentById) ID: {}", commentId);
        return Response.of(HttpStatus.OK.value(), commentService.getCommentById(postId, commentId));
    }

    @PutMapping("/{post_id}/comments/{comment_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response updateComment(@PathVariable("post_id") String postId, @PathVariable("comment_id") String commentId, @Validated @RequestBody CommentRequest request) {
        log.info("(updateComment) ID: {}, Request: {}", commentId, request);
        return Response.of(
                HttpStatus.CREATED.value(),
                commentService.updateComment(getUserId(),postId, commentId, request));
    }

    @GetMapping("/{post_id}/comments")
    public Response search(@PathVariable("post_id") String postId) {
        return Response.of(HttpStatus.OK.value(), commentService.getAllComments(postId));
    }
}
