package org.aibles.privatetraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aibles.privatetraining.dto.request.ImageRequest;
import org.aibles.privatetraining.dto.response.ImageResponse;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.exception.ImageNotFoundException;
import org.aibles.privatetraining.service.ImageService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(ImageController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ImageService imageService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserProfileService userProfileService;

    private ImageRequest imageRequest() {
        ImageRequest request = new ImageRequest();
        request.setUrl("http://example.com/image.jpg");
        request.setCaption("Sample caption");
        return request;
    }

    private ImageResponse imageResponse(String imageId) {
        return new ImageResponse(imageId, "http://example.com/image.jpg", "Sample caption");
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_CreateImage_WhenInputValid_Return201AndResponseBody() throws Exception {
        ImageRequest request = imageRequest();
        ImageResponse response = imageResponse("imageId");

        Mockito.when(imageService.createImage(any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        post("/api/v1/images")
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
    public void test_DeleteImage_WhenInputValid_Return200() throws Exception {
        String imageId = "imageId";

        Mockito.doNothing().when(imageService).deleteImage(imageId);

        mockMvc.perform(
                        delete("/api/v1/images/{id}", imageId)
                                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_DeleteImage_WhenImageIdNotFound_Return404() throws Exception {
        String imageId = "imageId";

        Mockito.doThrow(new ImageNotFoundException("imageId")).when(imageService).deleteImage(imageId);

        mockMvc.perform(
                        delete("/api/v1/images/{id}", imageId)
                                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_UpdateImage_WhenInputValid_Return201AndResponseBody() throws Exception {
        String imageId = "imageId";
        ImageRequest request = imageRequest();
        ImageResponse response = imageResponse(imageId);

        Mockito.when(imageService.updateImage(eq(imageId), any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        put("/api/v1/images/{id}", imageId)
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
    public void test_UpdateImage_WhenImageIdNotFound_Return404() throws Exception {
        String imageId = "imageId";
        ImageRequest request = imageRequest();

        Mockito.when(imageService.updateImage(eq(imageId), any())).thenThrow(new ImageNotFoundException("imageId"));

        mockMvc.perform(
                        put("/api/v1/images/{id}", imageId)
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetImageById_WhenImageExists_Return200AndResponseBody() throws Exception {
        String imageId = "imageId";
        ImageResponse response = imageResponse(imageId);

        Mockito.when(imageService.getImageById(imageId)).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/images/{id}", imageId)
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
    public void test_GetImageById_WhenImageIdNotFound_Return404() throws Exception {
        String imageId = "imageId";

        Mockito.when(imageService.getImageById(imageId)).thenThrow(new ImageNotFoundException("imageId"));

        mockMvc.perform(
                        get("/api/v1/images/{id}", imageId)
                                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void test_GetAllImages_WhenImagesExist_Return200AndResponseBody() throws Exception {
        List<ImageResponse> responses = List.of(imageResponse("imageId"));

        Mockito.when(imageService.getAllImages()).thenReturn(responses);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/images")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(responses));
    }

}
