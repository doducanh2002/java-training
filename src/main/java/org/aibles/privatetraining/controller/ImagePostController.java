package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ImagePostRequest;
import org.aibles.privatetraining.dto.response.ImagePostResponse;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.service.ImagePostService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/image_posts")
@RequiredArgsConstructor
public class ImagePostController {

    private final ImagePostService imagePostService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createImagePost(@Validated @RequestBody ImagePostRequest request) {
        log.info("(createImagePost) Request: {}", request);
        return Response.of(
                HttpStatus.CREATED.value(),
                imagePostService.createImagePost(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteImagePost(@PathVariable String id) {
        imagePostService.deleteImagePost(id);
        return Response.of(
                HttpStatus.OK.value(),
                "Delete image post successfully");
    }

    @GetMapping("/{id}")
    public Response getImagePostById(@PathVariable String id) {
        log.info("(getImagePostById) ID: {}", id);
        return Response.of(HttpStatus.OK.value(), imagePostService.getImagePostById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response updateImagePost(@PathVariable String id, @Validated @RequestBody ImagePostRequest request) {
        log.info("(updateImagePost) ID: {}, Request: {}", id, request);
        return Response.of(
                HttpStatus.CREATED.value(),
                imagePostService.updateImagePost(id, request));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(){
        return Response.of(HttpStatus.OK.value(), imagePostService.getAllImagePosts());
    }
}
