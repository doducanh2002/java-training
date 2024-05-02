package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ImageRequest;
import org.aibles.privatetraining.dto.response.ImageResponse;
import org.aibles.privatetraining.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    ImageResponse createImage(ImageRequest imageRequest);

    ImageResponse getImageById(String id);

    ImageResponse updateImage(String id, ImageRequest imageRequest);

    void deleteImage(String imageId);

    void checkImageId(String imageId);

    List<ImageResponse> getAllImages();

    List<ImageResponse> searchImage(String url, String caption);

    Image upload(MultipartFile multipartFile);
}
