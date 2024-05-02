package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ImageRequest;
import org.aibles.privatetraining.dto.response.ImageResponse;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createImage(@Validated @RequestBody ImageRequest request) {
        log.info("(createImage) Request: {}", request);
        return Response.of(
                HttpStatus.CREATED.value(),
                imageService.createImage(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteImage(@PathVariable String id) {
        imageService.deleteImage(id);
        return Response.of(
                HttpStatus.OK.value(),
                "Delete image successfully");
    }

    @GetMapping("/{id}")
    public Response getImageById(@PathVariable String id) {
        log.info("(getImageById) ID: {}", id);
        return Response.of(HttpStatus.OK.value(), imageService.getImageById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response updateImage(@PathVariable String id, @Validated @RequestBody ImageRequest request) {
        log.info("(updateImage) ID: {}, Request: {}", id, request);
        return Response.of(
                HttpStatus.CREATED.value(),
                imageService.updateImage(id, request));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(){
        return Response.of(HttpStatus.OK.value(), imageService.getAllImages());
    }

    @GetMapping("/search")
    public Response search(@RequestParam(required = false) String url,
                           @RequestParam(required = false) String caption) {
        return Response.of(HttpStatus.OK.value(), imageService.searchImage(url,caption));
    }

    @GetMapping("/testup")
    @ResponseStatus(HttpStatus.OK)
    public Response uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file) {
        log.info("(uploadFile)fileName: {}", file.getOriginalFilename());
        return Response.of(HttpStatus.OK.value(), imageService.upload(file));

    }
}
