package com.likelionproject.controller;

import com.likelionproject.service.AlgorithmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hello")
@Slf4j
@RequiredArgsConstructor
public class HelloController {

    private final AlgorithmService algorithmService;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("한건주");
    }

    @GetMapping("/{num}")
    public ResponseEntity<String> sumOfDigit(@PathVariable("num") int num) {
        int sum = algorithmService.sumOfDigit(num);
        return ResponseEntity.ok().body(String.format("sum: %d", sum));
    }

}
