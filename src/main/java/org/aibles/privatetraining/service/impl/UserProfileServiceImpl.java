package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.UserProfileRequest;
import org.aibles.privatetraining.dto.response.UserProfileResponse;
import org.aibles.privatetraining.entity.UserProfile;
import org.aibles.privatetraining.exception.EmailAlreadyExistedException;
import org.aibles.privatetraining.exception.UserNotFoundException;
import org.aibles.privatetraining.exception.UsernameAlreadyExistedException;
import org.aibles.privatetraining.repository.UserProfileRepository;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfileResponse createUser(UserProfileRequest request) {
      log.info("(createUser)request :{}", request);
      checkEmail(request.getEmail());
      checkUsername(request.getUsername());
      UserProfile userProfile = UserProfile.of(request);
      repository.save(userProfile);
      return UserProfileResponse.from(userProfile);
  }

    @Override
    public UserProfileResponse getById(String id) {
        log.info("(getOrderById)id: {}", id);
        var user =
                repository
                        .findById(id)
                        .orElseThrow(() -> {throw new UserNotFoundException(id);});
        return UserProfileResponse.from(user);
    }


    @Transactional
    @Override
    public void delete(String id){
        log.info("(delete)id: {}", id);
        repository.findById(id);
        if(!repository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public void checkUserId(String userId) {
        if (repository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public List<UserProfileResponse> getAll() {
        List<UserProfile> userProfiles = repository.findAll();
        return userProfiles.stream()
                .map(UserProfileResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserProfileResponse updateUser(String id, UserProfileRequest userProfileRequest) {
        log.info("(updateUser) id: {}, userProfileRequest: {}", id, userProfileRequest);
        var user = repository
                .findById(id)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(id);
                });
        user.setUsername(userProfileRequest.getUsername());
        user.setEmail(userProfileRequest.getEmail());
        repository.save(user);
        return UserProfileResponse.from(user);
    }

    void checkEmail(String email){
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistedException(email);
        }
    }

    void checkUsername(String username){
        if (repository.existsByUsername(username)) {
            throw new UsernameAlreadyExistedException(username);
        }
    }

}