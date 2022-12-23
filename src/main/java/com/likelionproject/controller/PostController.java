package com.likelionproject.controller;

import com.likelionproject.domain.dto.postdto.PostCreateRequest;
import com.likelionproject.domain.dto.postdto.PostModifyRequest;
import com.likelionproject.domain.dto.postdto.PostResponse;
import com.likelionproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> newPost(@RequestBody PostCreateRequest request, Authentication authentication) {
        return ResponseEntity.ok().body(postService.createPost(request, authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getOnePost(@PathVariable("id") Long id) {
        PostResponse postResponse = postService.getOnePost(id);
        return ResponseEntity.ok().body(postResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> modifyPost(@PathVariable("id") Long postId, @RequestBody PostModifyRequest postModifyRequest, Authentication authentication) {
        PostResponse postResponse = postService.modifyPost(postId, postModifyRequest, authentication.getName());
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable("id") Long deleteId, Authentication authentication) {
        PostResponse postResponse = postService.deletePost(deleteId, authentication.getName());
        return ResponseEntity.ok().body(postResponse);
    }

}
