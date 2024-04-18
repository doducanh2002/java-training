package org.aibles.privatetraining.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.privatetraining.dto.request.ImagePostRequest;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "image_post")
public class ImagePost {
    @Id
    private String id;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "imageId")
    private String imageId;


    @PrePersist
    private void prePersistId() {
        this.id = this.id== null ? UUID.randomUUID().toString() : this.id;
    }

    public static ImagePost of(ImagePostRequest request){
        ImagePost ip = new ImagePost();
        ip.setPostId(request.getPostId());
        ip.setImageId(request.getImageId());
        return ip;
    }
}
