package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test/1")
    public String testHello1() {
        return "hello";
    }

    @GetMapping("/test/2")
    public String testHello2() {
        return "hello";
    }
}