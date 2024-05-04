package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.CreatePostRequest;
import org.aibles.privatetraining.dto.request.PostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.facade.PostFacadeService;
import org.aibles.privatetraining.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.aibles.privatetraining.util.SecurityService.getUserId;

@RestController
@Slf4j
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostFacadeService postFacadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Validated @RequestBody CreatePostRequest request) {
        log.info("(createPost) Request: {}", request);
        return postFacadeService.createPostWithImage(getUserId(), request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable String id) {
        log.info("(getPostById) ID: {}", id);
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse updatePost(@PathVariable String id, @Validated @RequestBody PostRequest request) {
        log.info("(updatePost) ID: {}, Request: {}", id, request);
        return postService.updatePost(getUserId(), id, request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAll(){
        return postService.getAllPosts();
    }

    @GetMapping("/search")
    public List<PostResponse> search(@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String content) {
        return postService.searchPost(getUserId(), title, content);
    }
}
