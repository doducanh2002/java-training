package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ActiveOTPRequest;
import org.aibles.privatetraining.dto.request.SendOTPRequest;
import org.aibles.privatetraining.dto.request.UserProfileRequest;
import org.aibles.privatetraining.dto.request.UserRequest;
import org.aibles.privatetraining.dto.response.AuthenticationResponse;
import org.aibles.privatetraining.dto.response.UserProfileResponse;
import org.aibles.privatetraining.entity.UserProfile;

import java.util.List;

public interface UserProfileService {

  UserProfileResponse createUser(UserProfileRequest userProfileRequest);

  UserProfileResponse getById(String id);

  UserProfile register(UserRequest userRequest);

  void sendOTP(SendOTPRequest request);

  void verifyOTP(ActiveOTPRequest request);

  AuthenticationResponse login(UserRequest userRequest);

  void changePassword(String username, String newPassword);

  UserProfileResponse updateUser(String id, UserProfileRequest userProfileRequest);

  void delete(String userId);
  void checkUserId(String userId);

  List<UserProfileResponse> getAll();
  UserProfileResponse getByUsername(String username);


}