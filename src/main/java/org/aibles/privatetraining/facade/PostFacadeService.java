package org.aibles.privatetraining.facade;

import org.aibles.privatetraining.dto.request.CreatePostRequest;
import org.aibles.privatetraining.dto.response.PostResponse;

public interface PostFacadeService {

    PostResponse createPostWithImage(String userId, CreatePostRequest createPostRequest);
}
