package com.likelionproject.controller;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping
    public ResponseEntity<Response<String>> makeLike(@PathVariable("postId") Long postId, Authentication authentication) {
        Response<String> response = likeService.makeLike(postId, authentication.getName());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Response<Long>> showLikes(@PathVariable("postId") Long postId) {
        Response<Long> response = likeService.showLike(postId);
        return ResponseEntity.ok().body(response);
    }
}
