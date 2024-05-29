package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.response.CommentResponse;
import org.aibles.privatetraining.entity.Comment;
import org.aibles.privatetraining.exception.CommentNotFoundException;
import org.aibles.privatetraining.exception.PostNotFoundException;
import org.aibles.privatetraining.repository.CommentRepository;
import org.aibles.privatetraining.service.CommentService;
import org.aibles.privatetraining.service.PostService;
import org.aibles.privatetraining.service.UserProfileService;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@WebMvcTest(CommentService.class)
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private PostService postService;


    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private CommentRequest mockCommentRequest() {
        CommentRequest request = new CommentRequest();
        request.setContent("Sample comment content");
        return request;
    }

    private Comment mockCommentEntity(String userId, String postId) {
        Comment comment = Comment.of(mockCommentRequest());
        comment.setUserId(userId);
        comment.setPostId(postId);
        return comment;
    }

    @Test
    public void test_CreateComment_Successful() {
        String userId = "userId";
        String postId = "postId";
        CommentRequest mockRequest = mockCommentRequest();
        Comment mockEntity = mockCommentEntity(userId, postId);

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(mockEntity);

        CommentResponse response = commentService.createComment(userId, postId, mockRequest);

        Assertions.assertEquals(mockRequest.getContent(), response.getContent());
    }

    @Test
    public void test_CreateComment_PostNotFound() {
        String userId = "userId";
        String postId = "invalidPostId";
        CommentRequest mockRequest = mockCommentRequest();

        Mockito.doThrow(new PostNotFoundException(postId)).when(postService).checkPostId(postId);

        Assertions.assertThrows(PostNotFoundException.class, () -> commentService.createComment(userId, postId, mockRequest));
    }

    @Test
    public void test_GetCommentById_Successful() {
        String postId = "postId";
        String commentId = "commentId";
        Comment mockEntity = mockCommentEntity("userId", postId);

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.of(mockEntity));

        CommentResponse response = commentService.getCommentById(postId, commentId);

        Assertions.assertEquals(mockEntity.getContent(), response.getContent());
    }

    @Test
    public void test_GetCommentById_NotFound() {
        String postId = "postId";
        String commentId = "invalidCommentId";

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.getCommentById(postId, commentId));
    }

    @Test
    public void test_UpdateComment_Successful() {
        String userId = "userId";
        String postId = "postId";
        String commentId = "commentId";
        CommentRequest mockRequest = mockCommentRequest();
        Comment mockEntity = mockCommentEntity(userId, postId);

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.of(mockEntity));
        Mockito.when(commentRepository.save(mockEntity)).thenReturn(mockEntity);

        CommentResponse response = commentService.updateComment(userId, postId, commentId, mockRequest);

        Assertions.assertEquals(mockRequest.getContent(), response.getContent());
    }

    @Test
    public void test_UpdateComment_NotFound() {
        String userId = "userId";
        String postId = "postId";
        String commentId = "invalidCommentId";
        CommentRequest mockRequest = mockCommentRequest();

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.updateComment(userId, postId, commentId, mockRequest));
    }

    @Test
    public void test_DeleteComment_Successful() {
        String postId = "postId";
        String commentId = "commentId";
        Comment mockEntity = mockCommentEntity("userId", postId);

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.of(mockEntity));
        Mockito.doNothing().when(commentRepository).delete(mockEntity);

        Assertions.assertDoesNotThrow(() -> commentService.deleteComment(postId, commentId));
    }

    @Test
    public void test_DeleteComment_NotFound() {
        String postId = "postId";
        String commentId = "invalidCommentId";

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(postId, commentId));
    }

    @Test
    public void test_GetAllComments_Successful() {
        String postId = "postId";
        Comment mockEntity = mockCommentEntity("userId", postId);

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(commentRepository.findAllByPostId(postId)).thenReturn(List.of(mockEntity));

        List<CommentResponse> responses = commentService.getAllComments(postId);

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getContent(), responses.get(0).getContent());
    }

    @Test
    public void test_CheckCommentId_Exists() {
        String postId = "postId";
        String commentId = "commentId";

        Mockito.when(commentRepository.existsByIdAndPostId(commentId, postId)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> commentService.checkCommentId(postId, commentId));
    }

    @Test
    public void test_CheckCommentId_NotExists() {
        String postId = "postId";
        String commentId = "invalidCommentId";

        Mockito.when(commentRepository.existsByIdAndPostId(commentId, postId)).thenReturn(false);

        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.checkCommentId(postId, commentId));
    }
}
