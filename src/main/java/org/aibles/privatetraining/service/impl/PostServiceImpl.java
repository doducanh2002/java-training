package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.PostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.entity.Post;
import org.aibles.privatetraining.exception.PostNotFoundException;
import org.aibles.privatetraining.repository.PostRepository;
import org.aibles.privatetraining.service.PostService;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final UserProfileService userProfileService;

    @Autowired
    public PostServiceImpl(PostRepository repository, UserProfileService userProfileService) {
        this.repository = repository;
        this.userProfileService = userProfileService;
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        log.info("(createPost) Request: {}", postRequest);
        userProfileService.checkUserId(postRequest.getUserId());
        Post post = Post.of(postRequest);
        post.setCreatedAt(LocalDateTime.now());
        repository.save(post);
        return PostResponse.from(post);
    }

    @Override
    public PostResponse getPostById(String id) {
        log.info("(getPostById) ID: {}", id);
        Post post = repository.findById(id)
                             .orElseThrow(() -> new PostNotFoundException(id));
        return PostResponse.from(post);
    }

    @Override
    @Transactional
    public PostResponse updatePost(String id, PostRequest postRequest) {
        log.info("(updatePost) ID: {}, Request: {}", id, postRequest);
        userProfileService.checkUserId(postRequest.getUserId());
        Post post = repository.findById(id)
                             .orElseThrow(() -> new PostNotFoundException(id));
        post.setContent(postRequest.getContent());
        post.setTitle(postRequest.getTitle());
        repository.save(post);
        return PostResponse.from(post);
    }

    @Override
    public void deletePost(String postId) {
        log.info("(deletePost) ID: {}", postId);
        checkPostId(postId);
        repository.deleteById(postId);
    }

    @Override
    public void checkPostId(String postId) {
        if (!repository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = repository.findAll();
        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> searchPost(String userId, String title, String content) {
        List<Post> posts = repository.searchPost(userId, title, content);
        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}
