package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    @Query("SELECT i FROM Image i WHERE (:url is null or i.url = :url) " +
            "AND (:caption is null or i.caption = :caption)")
    List<Image> searchImage(String url, String caption);
}