package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.UserProfileRequest;
import org.aibles.privatetraining.dto.request.UserRequest;
import org.aibles.privatetraining.dto.response.AuthenticationResponse;
import org.aibles.privatetraining.dto.response.UserProfileResponse;
import org.aibles.privatetraining.entity.Role;
import org.aibles.privatetraining.entity.UserProfile;
import org.aibles.privatetraining.exception.EmailAlreadyExistedException;
import org.aibles.privatetraining.exception.UserNotFoundException;
import org.aibles.privatetraining.exception.UsernameAlreadyExistedException;
import org.aibles.privatetraining.repository.UserProfileRepository;
import org.aibles.privatetraining.service.JwtUserDetailsService;
import org.aibles.privatetraining.service.UserProfileService;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
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
    public UserProfile register(UserRequest userRequest) {
        UserProfile newUser = new UserProfile();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setRoles(Role.USER);
        return userProfileRepository.save(newUser);
    }


    @Override
    public AuthenticationResponse authenticate(UserRequest userRequest) throws Exception {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userRequest.getUsername());

        if (!jwtTokenUtil.validatePassword(userDetails, userRequest.getPassword())) {
            throw new Exception("Incorrect username or password");
        }

        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

        return new AuthenticationResponse(accessToken, refreshToken);
    }


    @Override
    public void changePassword(String username, String newPassword) {
        UserProfile user = userProfileRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userProfileRepository.save(user);
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