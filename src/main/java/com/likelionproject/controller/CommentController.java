package com.likelionproject.controller;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.commentdto.request.CommentCreateRequest;
import com.likelionproject.domain.dto.commentdto.result.CommentCreateResult;
import com.likelionproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Response<CommentCreateResult>> createComment(@PathVariable("postId") Long postId, @RequestBody CommentCreateRequest commentCreateRequest, Authentication authentication) {
        Response<CommentCreateResult> response = commentService.createComment(postId, commentCreateRequest, authentication);
        return ResponseEntity.ok().body(response);
    }



}
