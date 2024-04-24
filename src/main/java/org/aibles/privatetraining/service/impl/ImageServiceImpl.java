package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ImageRequest;
import org.aibles.privatetraining.dto.response.ImageResponse;
import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.exception.ImageNotFoundException;
import org.aibles.privatetraining.repository.ImageRepository;
import org.aibles.privatetraining.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Autowired
    public ImageServiceImpl(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public ImageResponse createImage(ImageRequest imageRequest) {
        log.info("(createImage) Request: {}", imageRequest);
        Image image = Image.of(imageRequest);
        repository.save(image);
        return ImageResponse.from(image);
    }

    @Override
    public ImageResponse getImageById(String id) {
        log.info("(getImageById) ID: {}", id);
        Image image = repository.findById(id)
                                .orElseThrow(() -> new ImageNotFoundException(id));
        return ImageResponse.from(image);
    }

    @Override
    @Transactional
    public ImageResponse updateImage(String id, ImageRequest imageRequest) {
        log.info("(updateImage) ID: {}, Request: {}", id, imageRequest);
        Image image = repository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
        image.setUrl(imageRequest.getUrl());
        image.setCaption(imageRequest.getCaption());
        repository.save(image);
        return ImageResponse.from(image);
    }

    @Override
    public void deleteImage(String imageId) {
        log.info("(deleteImage) ID: {}", imageId);
        checkImageId(imageId);
        repository.deleteById(imageId);
    }

    @Override
    public void checkImageId(String imageId) {
        if (!repository.existsById(imageId)) {
            throw new ImageNotFoundException(imageId);
        }
    }

    @Override
    public List<ImageResponse> getAllImages() {
        List<Image> image = repository.findAll();
        return image.stream()
                .map(ImageResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImageResponse> searchImage(String url, String caption) {
        List<Image> images = repository.searchImage(url, caption);
        return images.stream()
                .map(ImageResponse::from)
                .collect(Collectors.toList());
    }
}
