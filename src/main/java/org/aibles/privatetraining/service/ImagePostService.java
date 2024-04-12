package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ImagePostRequest;
import org.aibles.privatetraining.dto.response.ImagePostResponse;

import java.util.List;

public interface ImagePostService {

    ImagePostResponse createImagePost(ImagePostRequest imagePostRequest);

    ImagePostResponse getImagePostById(String id);

    ImagePostResponse updateImagePost(String id, ImagePostRequest imagePostRequest);

    void deleteImagePost(String postId);

    List<ImagePostResponse> getAllImagePosts();
}
