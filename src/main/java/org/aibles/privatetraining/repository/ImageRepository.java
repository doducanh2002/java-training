package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.Image;
import org.aibles.privatetraining.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}