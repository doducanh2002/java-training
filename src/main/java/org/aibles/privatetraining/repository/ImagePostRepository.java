package org.aibles.privatetraining.repository;

import org.aibles.privatetraining.entity.ImagePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePostRepository extends JpaRepository<ImagePost, String> {

}