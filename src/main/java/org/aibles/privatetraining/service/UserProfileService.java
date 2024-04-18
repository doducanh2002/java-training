package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.UserProfileRequest;
import org.aibles.privatetraining.dto.response.UserProfileResponse;

import java.util.List;

public interface UserProfileService {

  UserProfileResponse createUser(UserProfileRequest userProfileRequest);

  UserProfileResponse getById(String id);

  UserProfileResponse updateUser(String id, UserProfileRequest userProfileRequest);

  void delete(String userId);
  void checkUserId(String userId);

  List<UserProfileResponse> getAll();

}