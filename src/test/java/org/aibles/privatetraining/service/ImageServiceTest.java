package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ImageRequest;
import org.aibles.privatetraining.dto.response.ImageResponse;
import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.exception.BadRequestException;
import org.aibles.privatetraining.exception.ImageNotFoundException;
import org.aibles.privatetraining.repository.ImageRepository;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ImageService.class)
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @MockBean
    private ImageRepository imageRepository;

    private Path fileStorageLocation;


    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    public void setUp() {
        fileStorageLocation = Paths.get("fileStorage").toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private ImageRequest mockImageRequest() {
        ImageRequest request = new ImageRequest();
        request.setUrl("http://example.com/image.jpg");
        request.setCaption("Sample Image");
        return request;
    }

    private Image mockImageEntity() {
        Image image = new Image();
        image.setImageId("imageId");
        image.setUrl("http://example.com/image.jpg");
        image.setCaption("Sample Image");
        return image;
    }

    @Test
    public void test_CreateImage_Successful() {
        ImageRequest mockRequest = mockImageRequest();
        Image mockEntity = mockImageEntity();

        Mockito.when(imageRepository.save(ArgumentMatchers.any(Image.class))).thenReturn(mockEntity);

        ImageResponse response = imageService.createImage(mockRequest);

        Assertions.assertEquals(mockRequest.getUrl(), response.getUrl());
        Assertions.assertEquals(mockRequest.getCaption(), response.getCaption());
    }

    @Test
    public void test_GetImageById_Successful() {
        String id = "imageId";
        Image mockEntity = mockImageEntity();

        Mockito.when(imageRepository.findById(id)).thenReturn(Optional.of(mockEntity));

        ImageResponse response = imageService.getImageById(id);

        Assertions.assertEquals(mockEntity.getUrl(), response.getUrl());
        Assertions.assertEquals(mockEntity.getCaption(), response.getCaption());
    }

    @Test
    public void test_GetImageById_NotFound() {
        String id = "invalidImageId";

        Mockito.when(imageRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ImageNotFoundException.class, () -> imageService.getImageById(id));
    }

    @Test
    public void test_UpdateImage_Successful() {
        String id = "imageId";
        ImageRequest mockRequest = mockImageRequest();
        Image mockEntity = mockImageEntity();

        Mockito.when(imageRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        Mockito.when(imageRepository.save(mockEntity)).thenReturn(mockEntity);

        ImageResponse response = imageService.updateImage(id, mockRequest);

        Assertions.assertEquals(mockRequest.getUrl(), response.getUrl());
        Assertions.assertEquals(mockRequest.getCaption(), response.getCaption());
    }

    @Test
    public void test_UpdateImage_NotFound() {
        String id = "invalidImageId";
        ImageRequest mockRequest = mockImageRequest();

        Mockito.when(imageRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ImageNotFoundException.class, () -> imageService.updateImage(id, mockRequest));
    }

    @Test
    public void test_DeleteImage_Successful() {
        String id = "imageId";

        Mockito.when(imageRepository.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(imageRepository).deleteById(id);

        Assertions.assertDoesNotThrow(() -> imageService.deleteImage(id));
    }

    @Test
    public void test_DeleteImage_NotFound() {
        String id = "invalidImageId";

        Mockito.when(imageRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ImageNotFoundException.class, () -> imageService.deleteImage(id));
    }

    @Test
    public void test_GetAllImages_Successful() {
        Image mockEntity = mockImageEntity();

        Mockito.when(imageRepository.findAll()).thenReturn(List.of(mockEntity));

        List<ImageResponse> responses = imageService.getAllImages();

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getUrl(), responses.get(0).getUrl());
        Assertions.assertEquals(mockEntity.getCaption(), responses.get(0).getCaption());
    }

    @Test
    public void test_SearchImage_Successful() {
        String url = "http://example.com/image.jpg";
        String caption = "Sample Image";
        Image mockEntity = mockImageEntity();

        Mockito.when(imageRepository.searchImage(url, caption)).thenReturn(List.of(mockEntity));

        List<ImageResponse> responses = imageService.searchImage(url, caption);

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getUrl(), responses.get(0).getUrl());
        Assertions.assertEquals(mockEntity.getCaption(), responses.get(0).getCaption());
    }

    @Test
    public void test_UploadImage_Successful() {
        MultipartFile mockMultipartFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", "some-image-content".getBytes());
        Image mockEntity = mockImageEntity();

        Mockito.when(imageRepository.save(ArgumentMatchers.any(Image.class))).thenReturn(mockEntity);

        Image response = imageService.upload(mockMultipartFile);

        Assertions.assertEquals(mockMultipartFile.getOriginalFilename(), response.getUrl());
    }

    @Test
    public void test_UploadImage_Failed() {
        MultipartFile mockMultipartFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", (byte[]) null);

        Assertions.assertThrows(BadRequestException.class, () -> imageService.upload(mockMultipartFile));
    }
}
