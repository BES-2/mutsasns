package com.likelionproject.service;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.commentdto.request.CommentCreateRequest;
import com.likelionproject.domain.dto.commentdto.result.CommentCreateResult;
import com.likelionproject.domain.dto.commentdto.result.CommentResultFactory;
import com.likelionproject.domain.entity.Comment;
import com.likelionproject.domain.entity.Post;
import com.likelionproject.domain.entity.User;
import com.likelionproject.exception.AppException;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.repository.CommentRepository;
import com.likelionproject.repository.PostRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Response<CommentCreateResult> createComment(Long postId, CommentCreateRequest commentCreateRequest, Authentication authentication) {
        // id로 post 찾아냄
        Post selectedPost = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        // authentication으로 user꺼냄
        User writingUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));

        // comment를 만들어야지. 내용, postid, username 적어서
        Comment newComment = commentRepository.save(commentCreateRequest.toEntity(selectedPost, writingUser));

        // 저장된 comment정보 CommentCreateResult 팩토리로 넘기자
        CommentCreateResult commentCreateResult = CommentResultFactory.from(newComment);
        return Response.success(commentCreateResult);
    }
}
