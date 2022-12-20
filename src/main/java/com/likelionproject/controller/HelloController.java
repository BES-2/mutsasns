package com.likelionproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello");
    }
}