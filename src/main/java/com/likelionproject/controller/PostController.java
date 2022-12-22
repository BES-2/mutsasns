package com.likelionproject.controller;

import com.likelionproject.domain.dto.postdto.PostCreateRequest;
import com.likelionproject.domain.dto.postdto.PostCreateResponse;
import com.likelionproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostCreateResponse> newPost(@RequestBody PostCreateRequest request, Authentication authentication) {
        return ResponseEntity.ok().body(postService.CreatePost(request, authentication));
    }
}
