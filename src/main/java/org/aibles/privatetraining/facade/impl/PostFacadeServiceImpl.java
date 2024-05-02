package org.aibles.privatetraining.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.CreatePostRequest;
import org.aibles.privatetraining.dto.response.ImageResponse;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.entity.ImagePost;
import org.aibles.privatetraining.facade.PostFacadeService;
import org.aibles.privatetraining.repository.ImagePostRepository;
import org.aibles.privatetraining.repository.ImageRepository;
import org.aibles.privatetraining.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostFacadeServiceImpl implements PostFacadeService {


    private final PostService postService;
    private final ImagePostRepository imagePostRepository;
    private final ImageRepository imageRepository;

    public PostFacadeServiceImpl(PostService postService, ImagePostRepository imagePostRepository, ImageRepository imageRepository) {
        this.postService = postService;
        this.imagePostRepository = imagePostRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public PostResponse createPostWithImage(String userId, CreatePostRequest createPostRequest) {
        PostResponse postResponse = postService.createPost(userId, createPostRequest.getContent(), createPostRequest.getTitle(), createPostRequest.getParentId());

        List<Image> images = createPostRequest.getImages();
        if (images != null && !images.isEmpty()) {
            for (Image image : images) {
                // Tạo đối tượng ImagePost từ thông tin ảnh và postId
                ImagePost imagePost = new ImagePost();
                imagePost.setPostId(postResponse.getPostId());
                imagePost.setImageId(image.getImageId());
                // Lưu vào cơ sở dữ liệu
                imagePostRepository.save(imagePost);
            }
        }
        return postResponse;
    }

}
