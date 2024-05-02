package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.PostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;

import java.util.List;

public interface PostService {


    PostResponse createPost(String userId, String content, String title, String parentId);

    PostResponse getPostById(String id);

    PostResponse updatePost(String userId, String id, PostRequest postRequest);

    void deletePost(String postId);

    void checkPostId(String postId);

    List<PostResponse> getAllPosts();

    List<PostResponse> searchPost(String userId, String title, String content);
}
