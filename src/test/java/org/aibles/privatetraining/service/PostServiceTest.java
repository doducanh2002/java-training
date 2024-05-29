package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.PostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.entity.Post;
import org.aibles.privatetraining.exception.PostNotFoundException;
import org.aibles.privatetraining.repository.PostRepository;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@WebMvcTest(PostService.class)
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private PostRequest mockPostRequest() {
        PostRequest request = new PostRequest();
        request.setContent("Sample content");
        request.setTitle("Sample title");
        return request;
    }

    private Post mockPostEntity() {
        Post post = new Post();
        post.setPostId("postId");
        post.setUserId("userId");
        post.setContent("Sample content");
        post.setTitle("Sample title");
        return post;
    }

    @Test
    public void test_CreatePost_Successful() {
        String userId = "userId";
        String content = "Sample content";
        String title = "Sample title";
        String parentId = "parentId";
        Post mockEntity = mockPostEntity();

        Mockito.when(postRepository.save(ArgumentMatchers.any(Post.class))).thenReturn(mockEntity);

        PostResponse response = postService.createPost(userId, content, title, parentId);

        Assertions.assertEquals(content, response.getContent());
        Assertions.assertEquals(title, response.getTitle());
    }

    @Test
    public void test_GetPostById_Successful() {
        String id = "postId";
        Post mockEntity = mockPostEntity();

        Mockito.when(postRepository.findById(id)).thenReturn(Optional.of(mockEntity));

        PostResponse response = postService.getPostById(id);

        Assertions.assertEquals(mockEntity.getContent(), response.getContent());
        Assertions.assertEquals(mockEntity.getTitle(), response.getTitle());
    }

    @Test
    public void test_GetPostById_NotFound() {
        String id = "invalidPostId";

        Mockito.when(postRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(PostNotFoundException.class, () -> postService.getPostById(id));
    }

    @Test
    public void test_UpdatePost_Successful() {
        String userId = "userId";
        String id = "postId";
        PostRequest mockRequest = mockPostRequest();
        Post mockEntity = mockPostEntity();

        Mockito.when(postRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        Mockito.when(postRepository.save(mockEntity)).thenReturn(mockEntity);

        PostResponse response = postService.updatePost(userId, id, mockRequest);

        Assertions.assertEquals(mockRequest.getContent(), response.getContent());
        Assertions.assertEquals(mockRequest.getTitle(), response.getTitle());
    }

    @Test
    public void test_UpdatePost_NotFound() {
        String userId = "userId";
        String id = "invalidPostId";
        PostRequest mockRequest = mockPostRequest();

        Mockito.when(postRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(PostNotFoundException.class, () -> postService.updatePost(userId, id, mockRequest));
    }

    @Test
    public void test_DeletePost_Successful() {
        String id = "postId";

        Mockito.when(postRepository.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(postRepository).deleteById(id);

        Assertions.assertDoesNotThrow(() -> postService.deletePost(id));
    }

    @Test
    public void test_DeletePost_NotFound() {
        String id = "invalidPostId";

        Mockito.when(postRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(PostNotFoundException.class, () -> postService.deletePost(id));
    }

    @Test
    public void test_GetAllPosts_Successful() {
        Post mockEntity = mockPostEntity();

        Mockito.when(postRepository.findAll()).thenReturn(List.of(mockEntity));

        List<PostResponse> responses = postService.getAllPosts();

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getContent(), responses.get(0).getContent());
        Assertions.assertEquals(mockEntity.getTitle(), responses.get(0).getTitle());
    }

    @Test
    public void test_SearchPost_Successful() {
        String userId = "userId";
        String title = "Sample title";
        String content = "Sample content";
        Post mockEntity = mockPostEntity();

        Mockito.when(postRepository.searchPost(userId, title, content)).thenReturn(List.of(mockEntity));

        List<PostResponse> responses = postService.searchPost(userId, title, content);

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getContent(), responses.get(0).getContent());
        Assertions.assertEquals(mockEntity.getTitle(), responses.get(0).getTitle());
    }
}
