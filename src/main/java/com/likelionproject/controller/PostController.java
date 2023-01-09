package com.likelionproject.controller;

import com.likelionproject.domain.dto.myfeeddto.MyFeedResult;
import com.likelionproject.domain.dto.postdto.result.*;
import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.postdto.request.PostCreateRequest;
import com.likelionproject.domain.dto.postdto.request.PostModifyRequest;
import com.likelionproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Response<PostCreateResult>> newPost(@RequestBody PostCreateRequest request, Authentication authentication) {
        Response<PostCreateResult> response = postService.createPost(request, authentication);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Response<PostGetResult>> getOnePost(@PathVariable("postId") Long id) {
        Response<PostGetResult> postResponse = postService.getOnePost(id);
        return ResponseEntity.ok().body(postResponse);
    }

    @GetMapping
    public ResponseEntity<Response<PostPageResult>> getPosts(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Response<PostPageResult> response = postService.getPosts(pageable);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<PostModifyRequest>> modifyPost(@PathVariable("id") Long postId, @RequestBody PostModifyRequest postModifyRequest, Authentication authentication) {
        Response response = postService.modifyPost(postId, postModifyRequest, authentication.getName());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<PostDeleteResult>> deletePost(@PathVariable("id") Long deleteId, Authentication authentication) {
        Response<PostDeleteResult> response = postService.deletePost(deleteId, authentication.getName());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/my")
    public ResponseEntity<Response<MyFeedResult>> getMyFeed(Authentication authentication, @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Response<MyFeedResult> response = postService.getMyFeed(authentication.getName(), pageable);
        return ResponseEntity.ok().body(response);
    }

}
