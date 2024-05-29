package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ImagePostRequest;
import org.aibles.privatetraining.dto.response.ImagePostResponse;
import org.aibles.privatetraining.entity.ImagePost;
import org.aibles.privatetraining.exception.ImagePostNotFoundException;
import org.aibles.privatetraining.repository.ImagePostRepository;
import org.aibles.privatetraining.service.ImagePostService;
import org.aibles.privatetraining.service.ImageService;
import org.aibles.privatetraining.service.PostService;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@WebMvcTest(ImagePostService.class)
public class ImagePostServiceTest {

    @Autowired
    private ImagePostService imagePostService;

    @MockBean
    private ImagePostRepository imagePostRepository;

    @MockBean
    private ImageService imageService;

    @MockBean
    private PostService postService;

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private ImagePostRequest mockImagePostRequest() {
        ImagePostRequest request = new ImagePostRequest();
        request.setImageId("imageId");
        request.setPostId("postId");
        return request;
    }

    private ImagePost mockImagePostEntity() {
        return ImagePost.of(mockImagePostRequest());
    }

    @Test
    public void test_CreateImagePost_Successful() {
        ImagePostRequest mockRequest = mockImagePostRequest();
        ImagePost mockEntity = mockImagePostEntity();

        Mockito.doNothing().when(imageService).checkImageId(mockRequest.getImageId());
        Mockito.doNothing().when(postService).checkPostId(mockRequest.getPostId());
        Mockito.when(imagePostRepository.save(Mockito.any(ImagePost.class))).thenReturn(mockEntity);

        ImagePostResponse response = imagePostService.createImagePost(mockRequest);

        Assertions.assertEquals(mockRequest.getImageId(), response.getImageId());
        Assertions.assertEquals(mockRequest.getPostId(), response.getPostId());
    }

    @Test
    public void test_CreateImagePost_ImageNotFound() {
        ImagePostRequest mockRequest = mockImagePostRequest();
        Mockito.doThrow(new RuntimeException("Image not found")).when(imageService).checkImageId(mockRequest.getImageId());

        Assertions.assertThrows(RuntimeException.class, () -> imagePostService.createImagePost(mockRequest));
    }

    @Test
    public void test_CreateImagePost_PostNotFound() {
        ImagePostRequest mockRequest = mockImagePostRequest();
        Mockito.doNothing().when(imageService).checkImageId(mockRequest.getImageId());
        Mockito.doThrow(new RuntimeException("Post not found")).when(postService).checkPostId(mockRequest.getPostId());

        Assertions.assertThrows(RuntimeException.class, () -> imagePostService.createImagePost(mockRequest));
    }

    @Test
    public void test_GetImagePostById_Successful() {
        String id = "imagePostId";
        ImagePost mockEntity = mockImagePostEntity();

        Mockito.when(imagePostRepository.findById(id)).thenReturn(Optional.of(mockEntity));

        ImagePostResponse response = imagePostService.getImagePostById(id);

        Assertions.assertEquals(mockEntity.getImageId(), response.getImageId());
        Assertions.assertEquals(mockEntity.getPostId(), response.getPostId());
    }

    @Test
    public void test_GetImagePostById_NotFound() {
        String id = "invalidImagePostId";

        Mockito.when(imagePostRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ImagePostNotFoundException.class, () -> imagePostService.getImagePostById(id));
    }

    @Test
    public void test_UpdateImagePost_Successful() {
        String id = "imagePostId";
        ImagePostRequest mockRequest = mockImagePostRequest();
        ImagePost mockEntity = mockImagePostEntity();

        Mockito.doNothing().when(imageService).checkImageId(mockRequest.getImageId());
        Mockito.doNothing().when(postService).checkPostId(mockRequest.getPostId());
        Mockito.when(imagePostRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        Mockito.when(imagePostRepository.save(mockEntity)).thenReturn(mockEntity);

        ImagePostResponse response = imagePostService.updateImagePost(id, mockRequest);

        Assertions.assertEquals(mockRequest.getImageId(), response.getImageId());
        Assertions.assertEquals(mockRequest.getPostId(), response.getPostId());
    }

    @Test
    public void test_UpdateImagePost_NotFound() {
        String id = "invalidImagePostId";
        ImagePostRequest mockRequest = mockImagePostRequest();

        Mockito.when(imagePostRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ImagePostNotFoundException.class, () -> imagePostService.updateImagePost(id, mockRequest));
    }

    @Test
    public void test_DeleteImagePost_Successful() {
        String id = "imagePostId";

        Mockito.when(imagePostRepository.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(imagePostRepository).deleteById(id);

        Assertions.assertDoesNotThrow(() -> imagePostService.deleteImagePost(id));
    }

    @Test
    public void test_DeleteImagePost_NotFound() {
        String id = "invalidImagePostId";

        Mockito.when(imagePostRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ImagePostNotFoundException.class, () -> imagePostService.deleteImagePost(id));
    }

    @Test
    public void test_GetAllImagePosts_Successful() {
        ImagePost mockEntity = mockImagePostEntity();

        Mockito.when(imagePostRepository.findAll()).thenReturn(List.of(mockEntity));

        List<ImagePostResponse> responses = imagePostService.getAllImagePosts();

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getImageId(), responses.get(0).getImageId());
        Assertions.assertEquals(mockEntity.getPostId(), responses.get(0).getPostId());
    }
}
