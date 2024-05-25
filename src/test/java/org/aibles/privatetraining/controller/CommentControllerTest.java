package org.aibles.privatetraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aibles.privatetraining.dto.request.CommentRequest;
import org.aibles.privatetraining.dto.response.CommentResponse;
import org.aibles.privatetraining.exception.CommentNotFoundException;
import org.aibles.privatetraining.exception.PostNotFoundException;
import org.aibles.privatetraining.service.CommentService;
import org.aibles.privatetraining.service.UserProfileService;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    private CommentRequest commentRequest() {
        CommentRequest request = new CommentRequest();
        request.setContent("Sample comment content");
        return request;
    }

    private CommentResponse commentResponse(String commentId) {
        Instant now = Instant.now();
        return new CommentResponse(commentId, "postId", "userId","tesst comment",now,now);
    }

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserProfileService userProfileService;

    @Test
//    @WithMockUser(username = "testUser", roles = {"CUSTOMER"})
//    @AutoConfigureMockMvc(addFilters = false)
    public void test_CreateComment_WhenInputValid_Return201AndResponseBody() throws Exception {
        String postId = "postId";
        CommentRequest request = commentRequest();
        CommentResponse response = commentResponse("commentId");

        Mockito.when(commentService.createComment(any(), eq(postId), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        post("/api/v1/posts/{postId}/comments", postId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        int status = 201;
        Assertions.assertEquals(201, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(response));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_CreateComment_WhenPostIdNotFound_Return404() throws Exception {
        String postId = "postId";
        CommentRequest request = commentRequest();

        Mockito.when(commentService.createComment(any(), eq(postId), any())).thenThrow(new PostNotFoundException("postId"));

        mockMvc.perform(
                        post("/api/v1/posts/{post_id}/comments", postId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_DeleteComment_WhenInputValid_Return200() throws Exception {
        String postId = "postId";
        String commentId = "commentId";

        Mockito.doNothing().when(commentService).deleteComment(postId, commentId);

        mockMvc.perform(
                        delete("/api/v1/posts/{post_id}/comments/{comment_id}", postId, commentId)
                                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_DeleteComment_WhenCommentIdNotFound_Return404() throws Exception {
        String postId = "postId";
        String commentId = "commentId";

        Mockito.doThrow(new CommentNotFoundException("commentId")).when(commentService).deleteComment(postId, commentId);

        mockMvc.perform(
                        delete("/api/v1/posts/{post_id}/comments/{comment_id}", postId, commentId)
                                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_UpdateComment_WhenInputValid_Return201AndResponseBody() throws Exception {
        String postId = "postId";
        String commentId = "commentId";
        CommentRequest request = commentRequest();
        CommentResponse response = commentResponse(commentId);

        Mockito.when(commentService.updateComment(any(), eq(postId), eq(commentId), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        put("/api/v1/posts/{post_id}/comments/{comment_id}", postId, commentId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(response));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_UpdateComment_WhenCommentIdNotFound_Return404() throws Exception {
        String postId = "postId";
        String commentId = "commentId";
        CommentRequest request = commentRequest();

        Mockito.when(commentService.updateComment(any(), eq(postId), eq(commentId), any())).thenThrow(new CommentNotFoundException("commentId"));

        mockMvc.perform(
                        put("/api/v1/posts/{post_id}/comments/{comment_id}", postId, commentId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_GetCommentById_WhenCommentExists_Return200AndResponseBody() throws Exception {
        String postId = "postId";
        String commentId = "commentId";
        CommentResponse response = commentResponse(commentId);

        Mockito.when(commentService.getCommentById(postId, commentId)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts/{post_id}/comments/{comment_id}", postId, commentId)
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(response));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetCommentById_WhenCommentIdNotFound_Return404() throws Exception {
        String postId = "postId";
        String commentId = "commentId";

        Mockito.when(commentService.getCommentById(postId, commentId)).thenThrow(new CommentNotFoundException("commentId"));

        mockMvc.perform(
                        get("/api/v1/posts/{post_id}/comments/{comment_id}", postId, commentId)
                                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetAllComments_WhenCommentsExist_Return200AndResponseBody() throws Exception {
        String postId = "postId";
        List<CommentResponse> responses = List.of(commentResponse("commentId"));

        Mockito.when(commentService.getAllComments(postId)).thenReturn(responses);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts/{post_id}/comments", postId)
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(responses));
    }
}
