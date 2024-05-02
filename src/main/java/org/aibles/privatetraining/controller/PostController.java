package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.CreatePostRequest;
import org.aibles.privatetraining.dto.request.PostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.facade.PostFacadeService;
import org.aibles.privatetraining.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Response createPost(@Validated @RequestBody CreatePostRequest request) {
        log.info("(createPost) Request: {}", request);
        return Response.of(
                HttpStatus.CREATED.value(),
                postFacadeService.createPostWithImage(getUserId(),request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return Response.of(
                HttpStatus.OK.value(),
                "Delete post successfully");
    }

    @GetMapping("/{id}")
    public Response getPostById(@PathVariable String id) {
        log.info("(getPostById) ID: {}", id);
        return Response.of(HttpStatus.OK.value(), postService.getPostById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response updatePost(@PathVariable String id, @Validated @RequestBody PostRequest request) {
        log.info("(updatePost) ID: {}, Request: {}", id, request);
        return Response.of(
                HttpStatus.CREATED.value(),
                postService.updatePost(getUserId(), id, request));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(){
        return Response.of(HttpStatus.OK.value(), postService.getAllPosts());
    }

    @GetMapping("/search")
    public Response search(     @RequestParam(required = false) String title,
                                @RequestParam(required = false) String content) {
        return Response.of(HttpStatus.OK.value(), postService.searchPost(getUserId(),title,content));
    }
}
