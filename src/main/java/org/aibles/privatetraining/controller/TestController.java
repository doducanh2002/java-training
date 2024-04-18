package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.UserRequest;
import org.aibles.privatetraining.dto.response.AuthenticationResponse;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private UserProfileService userService;

    @GetMapping("")
    public String test() {
        return "hello";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserRequest userRequest) {
        try {
            AuthenticationResponse authenticationResponse = userService.authenticate(userRequest);
            return ResponseEntity.ok(authenticationResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        log.info("(register)userRequest: {}", userRequest);
        try {
            userService.register(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}