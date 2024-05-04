package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.*;
import org.aibles.privatetraining.dto.response.*;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserProfileResponse createUser(@Validated @RequestBody UserProfileRequest request) {
    log.info("(create)request: {}", request);
    return service.createUser(request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable String userId) {
    service.delete(userId);
  }

  @GetMapping("/{userId}")
  public UserProfileResponse getById(@PathVariable String userId){
    log.info("(getById)id: {}",userId);
    return service.getById(userId);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public UserProfileResponse update(@PathVariable String id, @Validated @RequestBody UserProfileRequest request) {
    log.info("(update)request: {}", request);
    return service.updateUser(id,request);
  }


  @GetMapping("/login")
  public AuthenticationResponse authenticate(@Validated @RequestBody LoginRequest request) {
    return service.login(request);
  }

  @GetMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@Validated @RequestBody UserRequest userRequest) {
    log.info("(register)userRequest: {}", userRequest);
    service.register(userRequest);
  }

  @GetMapping("/send_otp")
  @ResponseStatus(HttpStatus.OK)
  public void sendOTP(@Validated @RequestBody SendOTPRequest request) {
    service.sendOTP(request);
  }


  @GetMapping("/active")
  @ResponseStatus(HttpStatus.OK)
  public void active(@Validated @RequestBody ActiveOTPRequest request) {
    service.verifyOTP(request);
  }

  @GetMapping("/search")
  public List<UserResponse> searchUsers(@RequestParam(required = false) String username,
                                        @RequestParam(required = false) String email) {
    return service.searchUserProfile(username,email);
  }

  @GetMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  public void forgotPassword(@Validated @RequestBody ForgotPasswordRequest request) {
    service.forgotPassword(request);
  }
}
