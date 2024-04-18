package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ImagePostRequest;
import org.aibles.privatetraining.dto.response.ImagePostResponse;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.entity.ImagePost;
import org.aibles.privatetraining.entity.Post;
import org.aibles.privatetraining.exception.ImagePostNotFoundException;
import org.aibles.privatetraining.repository.ImagePostRepository;
import org.aibles.privatetraining.service.ImagePostService;
import org.aibles.privatetraining.service.ImageService;
import org.aibles.privatetraining.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImagePostServiceImpl implements ImagePostService {

    private final ImagePostRepository repository;
    private final ImageService imageService;
    private final PostService postService;

    @Autowired
    public ImagePostServiceImpl(ImagePostRepository repository, ImageService imageService, PostService postService) {
        this.repository = repository;
        this.imageService = imageService;
        this.postService = postService;
    }

    @Override
    public ImagePostResponse createImagePost(ImagePostRequest imagePostRequest) {
        log.info("(createImagePost) Request: {}", imagePostRequest);
        imageService.checkImageId(imagePostRequest.getImageId());
        postService.checkPostId(imagePostRequest.getPostId());
        ImagePost imagePost = ImagePost.of(imagePostRequest);
        repository.save(imagePost);
        return ImagePostResponse.from(imagePost);
    }

    @Override
    public ImagePostResponse getImagePostById(String id) {
        log.info("(getImagePostById) ID: {}", id);
        ImagePost imagePost = repository.findById(id)
                                       .orElseThrow(() -> new ImagePostNotFoundException(id));
        return ImagePostResponse.from(imagePost);
    }

    @Override
    @Transactional
    public ImagePostResponse updateImagePost(String id, ImagePostRequest imagePostRequest) {
        log.info("(updateImagePost) ID: {}, Request: {}", id, imagePostRequest);
        imageService.checkImageId(imagePostRequest.getImageId());
        postService.checkPostId(imagePostRequest.getPostId());
        ImagePost imagePost = repository.findById(id)
                                            .orElseThrow(() -> new ImagePostNotFoundException(id));
        imagePost.setPostId(imagePostRequest.getPostId());
        imagePost.setImageId(imagePostRequest.getImageId());
        repository.save(imagePost);
        return ImagePostResponse.from(imagePost);
    }

    @Override
    public void deleteImagePost(String postId) {
        log.info("(deleteImagePost) ID: {}", postId);
        if(!repository.existsById(postId)) {
            throw new ImagePostNotFoundException (postId);
        }
        repository.deleteById(postId);
    }

    @Override
    public List<ImagePostResponse> getAllImagePosts() {
        List<ImagePost> postImage = repository.findAll();
        return postImage.stream()
                .map(ImagePostResponse::from)
                .collect(Collectors.toList());

    }
}
