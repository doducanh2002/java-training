package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.ImageRequest;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "image_id")
    private String imageId;

    @Column(name = "url")
    private String url;

    @Column(name = "caption")
    private String caption;

    @PrePersist
    private void prePersistId() {
        this.imageId = this.imageId== null ? UUID.randomUUID().toString() : this.imageId;
    }

    public static Image of(ImageRequest imageRequest) {
        Image image = new Image();
        image.setUrl(imageRequest.getUrl());
        image.setCaption(imageRequest.getCaption());
        return image;
    }

    public static Image of(String caption, String url) {
        Image image = new Image();
        image.setUrl(url);
        image.setCaption(caption);
        return image;
    }
}
