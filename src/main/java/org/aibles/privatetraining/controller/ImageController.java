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

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse createImage(@Validated @RequestBody ImageRequest request) {
        log.info("(createImage) Request: {}", request);
        return imageService.createImage(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteImage(@PathVariable String id) {
        imageService.deleteImage(id);
        return "Delete image successfully";
    }

    @GetMapping("/{id}")
    public ImageResponse getImageById(@PathVariable String id) {
        log.info("(getImageById) ID: {}", id);
        return imageService.getImageById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse updateImage(@PathVariable String id, @Validated @RequestBody ImageRequest request) {
        log.info("(updateImage) ID: {}, Request: {}", id, request);
        return imageService.updateImage(id, request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ImageResponse> getAll(){
        return imageService.getAllImages();
    }

    @GetMapping("/search")
    public List<ImageResponse> search(@RequestParam(required = false) String url,
                                      @RequestParam(required = false) String caption) {
        return imageService.searchImage(url,caption);
    }

    @GetMapping("/testup")
    @ResponseStatus(HttpStatus.OK)
    public Response uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file) {
        log.info("(uploadFile)fileName: {}", file.getOriginalFilename());
        return Response.of(HttpStatus.OK.value(), imageService.upload(file));
    }
}
