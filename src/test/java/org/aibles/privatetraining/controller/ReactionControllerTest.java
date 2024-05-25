package org.aibles.privatetraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;
import org.aibles.privatetraining.service.ReactionService;
import org.aibles.privatetraining.service.UserProfileService;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReactionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReactionService reactionService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserProfileService userProfileService;

    private ReactionRequest createReactionRequest() {
        ReactionRequest request = new ReactionRequest();
        request.setReactionType("LIKE");
        return request;
    }

    private ReactionResponse createReactionResponse(String postId, String reactionId) {
        Instant now = Instant.now();
        return new ReactionResponse(
                reactionId,
                postId,
                "userId",
                "LIKE",
                now,
                now
        );
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_CreateReaction_WhenInputValid_Return201AndResponseBody() throws Exception {
        String postId = "postId";
        ReactionRequest request = createReactionRequest();
        ReactionResponse response = createReactionResponse(postId, "reactionId");

        Mockito.when(reactionService.createReaction(any(), eq(postId), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        post("/api/v1/posts/{post_id}/reactions", postId)
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
    public void test_DeleteReaction_WhenInputValid_Return200() throws Exception {
        String postId = "postId";
        String reactionId = "reactionId";

        Mockito.doNothing().when(reactionService).deleteReaction(postId, reactionId);

        mockMvc.perform(
                        delete("/api/v1/posts/{post_id}/reactions/{id}", postId, reactionId)
                                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetReactionById_WhenReactionExists_Return200AndResponseBody() throws Exception {
        String postId = "postId";
        String reactionId = "reactionId";
        ReactionResponse response = createReactionResponse(postId, reactionId);

        Mockito.when(reactionService.getReactionById(postId, reactionId)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts/{post_id}/reactions/{id}", postId, reactionId)
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
    public void test_UpdateReaction_WhenInputValid_Return201AndResponseBody() throws Exception {
        String postId = "postId";
        String reactionId = "reactionId";
        ReactionRequest request = createReactionRequest();
        ReactionResponse response = createReactionResponse(postId, reactionId);

        Mockito.when(reactionService.updateReaction(any(), eq(postId), eq(reactionId), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        put("/api/v1/posts/{post_id}/reactions/{id}", postId, reactionId)
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
    public void test_GetReactionsByPostId_WhenReactionsExist_Return200AndResponseBody() throws Exception {
        String postId = "postId";
        List<ReactionResponse> responses = List.of(createReactionResponse(postId, "reactionId"));

        Mockito.when(reactionService.searchReaction(postId)).thenReturn(responses);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts/{post_id}/reactions", postId)
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(responses));
    }
}
