package org.aibles.privatetraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aibles.privatetraining.dto.request.CreatePostRequest;
import org.aibles.privatetraining.dto.request.PostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.exception.PostNotFoundException;
import org.aibles.privatetraining.facade.PostFacadeService;
import org.aibles.privatetraining.service.PostService;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

//import static org.aibles.privatetraining.util.SecurityService.getUserId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @MockBean
    private PostFacadeService postFacadeService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserProfileService userProfileService;

    private CreatePostRequest createPostRequest() {
        CreatePostRequest request = new CreatePostRequest();
        request.setTitle("Sample Title");
        request.setContent("Sample Content");
        return request;
    }

    private PostRequest postRequest() {
        PostRequest request = new PostRequest();
        request.setTitle("Updated Title");
        request.setContent("Updated Content");
        return request;
    }

    private PostResponse postResponse(String postId) {
        LocalDateTime now = LocalDateTime.now();
        return new PostResponse(
                postId,
                "userId",
                "Test Post",
                "title",
                Collections.emptyList(),
                now
        );
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_CreatePost_WhenInputValid_Return201AndResponseBody() throws Exception {
        CreatePostRequest request = createPostRequest();
        PostResponse response = postResponse("postId");

        Mockito.when(postFacadeService.createPostWithImage(any(), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        post("/api/v1/posts")
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
    public void test_DeletePost_WhenInputValid_Return200() throws Exception {
        String postId = "postId";

        Mockito.doNothing().when(postService).deletePost(postId);

        mockMvc.perform(
                        delete("/api/v1/posts/{id}", postId)
                                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_DeletePost_WhenPostIdNotFound_Return404() throws Exception {
        String postId = "postId";

        Mockito.doThrow(new PostNotFoundException("postId")).when(postService).deletePost(postId);

        mockMvc.perform(
                        delete("/api/v1/posts/{id}", postId)
                                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_UpdatePost_WhenInputValid_Return201AndResponseBody() throws Exception {
        String postId = "postId";
        PostRequest request = postRequest();
        PostResponse response = postResponse(postId);

        Mockito.when(postService.updatePost("usertId", eq(postId), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        put("/api/v1/posts/{id}", postId)
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
    public void test_UpdatePost_WhenPostIdNotFound_Return404() throws Exception {
        String postId = "postId";
        PostRequest request = postRequest();

        Mockito.when(postService.updatePost("userId", eq(postId), any())).thenThrow(new PostNotFoundException("postId"));

        mockMvc.perform(
                        put("/api/v1/posts/{id}", postId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetPostById_WhenPostExists_Return200AndResponseBody() throws Exception {
        String postId = "postId";
        PostResponse response = postResponse(postId);

        Mockito.when(postService.getPostById(postId)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts/{id}", postId)
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
    public void test_GetPostById_WhenPostIdNotFound_Return404() throws Exception {
        String postId = "postId";

        Mockito.when(postService.getPostById(postId)).thenThrow(new PostNotFoundException("postId"));

        mockMvc.perform(
                        get("/api/v1/posts/{id}", postId)
                                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetAllPosts_WhenPostsExist_Return200AndResponseBody() throws Exception {
        List<PostResponse> responses = List.of(postResponse("postId"));

        Mockito.when(postService.getAllPosts()).thenReturn(responses);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(responses));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_SearchPosts_WhenPostsExist_Return200AndResponseBody() throws Exception {
        String title = "Sample Title";
        String content = "Sample Content";
        List<PostResponse> responses = List.of(postResponse("postId"));

        Mockito.when(postService.searchPost("userId", eq(title), eq(content))).thenReturn(responses);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/posts/search")
                                .param("title", title)
                                .param("content", content)
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(responses));
    }
}
