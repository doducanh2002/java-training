package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ImagePostRequest;
import org.aibles.privatetraining.dto.response.ImagePostResponse;
import org.aibles.privatetraining.service.ImagePostService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/image_posts")
@RequiredArgsConstructor
public class ImagePostController {

    private final ImagePostService imagePostService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImagePostResponse createImagePost(@Validated @RequestBody ImagePostRequest request) {
        log.info("(createImagePost) Request: {}", request);
        return imagePostService.createImagePost(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImagePost(@PathVariable String id) {
        imagePostService.deleteImagePost(id);
    }

    @GetMapping("/{id}")
    public ImagePostResponse getImagePostById(@PathVariable String id) {
        log.info("(getImagePostById) ID: {}", id);
        return imagePostService.getImagePostById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ImagePostResponse updateImagePost(@PathVariable String id, @Validated @RequestBody ImagePostRequest request) {
        log.info("(updateImagePost) ID: {}, Request: {}", id, request);
        return imagePostService.updateImagePost(id, request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ImagePostResponse> getAll(){
        return imagePostService.getAllImagePosts();
    }
}
