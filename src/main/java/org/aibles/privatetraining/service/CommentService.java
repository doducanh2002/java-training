package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse createComment(String postId, CommentRequest commentRequest);

    CommentResponse getCommentById(String postId, String commentId);

    CommentResponse updateComment(String postId, String commentId, CommentRequest commentRequest);

    void deleteComment(String postId, String commentId);

    void checkCommentId(String postId, String commentId);

    List<CommentResponse> getAllComments(String postId);

}
