package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.*;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@Validated @RequestBody UserProfileRequest request) {
    log.info("(create)request: {}", request);
    return Response.of(
        HttpStatus.CREATED.value(),
        service.createUser(request));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response delete(@PathVariable String userId) {
    service.delete(userId);
    return Response.of(
        HttpStatus.OK.value(), "Delete shopping cart successfully");
  }

  @GetMapping("/{userId}")
  public Response getById(@PathVariable String userId){
    log.info("(getById)id: {}",userId);
    return Response.of(HttpStatus.OK.value(), service.getById(userId));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Response update(@PathVariable String id, @Validated @RequestBody UserProfileRequest request) {
    log.info("(update)request: {}", request);
    return Response.of(
            HttpStatus.CREATED.value(),
            service.updateUser(id,request));
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response getAll(){
    return Response.of(HttpStatus.OK.value(), service.getAll());
  }


  @GetMapping("/login")
  public Response authenticate(@Validated @RequestBody LoginRequest request) {
    return Response.of(HttpStatus.OK.value(), service.login(request));

  }

  @GetMapping("/register")
  public Response register(@Validated @RequestBody UserRequest userRequest) {
    log.info("(register)userRequest: {}", userRequest);
    service.register(userRequest);
    return Response.of(HttpStatus.CREATED.value(),"User registered successfully");
  }

  @GetMapping("/send_otp")
  @ResponseStatus(HttpStatus.OK)
  public Response sendOTP(@Validated @RequestBody SendOTPRequest request) {
    service.sendOTP(request);
    return Response.of(HttpStatus.OK.value(), "Send OTP successfully");
  }


  @GetMapping("/active")
  @ResponseStatus(HttpStatus.OK)
  public Response active(@Validated @RequestBody ActiveOTPRequest request) {
    service.verifyOTP(request);
    return Response.of(HttpStatus.OK.value(), "Active successfully");
  }

  @GetMapping("/search")
  public Response searchUsers(@RequestParam(required = false) String username,
                                               @RequestParam(required = false) String email) {
    return Response.of(HttpStatus.OK.value(), service.searchUserProfile(username,email));
  }

  @GetMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  public Response forgotPassword(@Validated @RequestBody ForgotPasswordRequest request) {
    service.forgotPassword(request);
    return Response.of(HttpStatus.OK.value(), "Success");
  }
}
