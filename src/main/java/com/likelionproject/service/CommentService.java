package com.likelionproject.service;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.commentdto.request.CommentCreateRequest;
import com.likelionproject.domain.dto.commentdto.request.CommentModifyRequest;
import com.likelionproject.domain.dto.commentdto.result.CommentCreateResult;
import com.likelionproject.domain.dto.commentdto.result.CommentModifyResult;
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
        Post selectedPost = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUNDED));
        User writingUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));

        Comment newComment = commentRepository.save(commentCreateRequest.toEntity(selectedPost, writingUser));
        CommentCreateResult commentCreateResult = CommentResultFactory.from(newComment);

        return Response.success(commentCreateResult);
    }

    public Response<CommentModifyResult> modifyComment(Long commentId, CommentModifyRequest commentModifyRequest, Authentication authentication) {
        User modifyingUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));
        Comment modifyComment = commentRepository.findById(commentId)
                .filter(comment -> comment.getUser().getId() == modifyingUser.getId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));

        modifyComment.modifyComment(commentModifyRequest);

        CommentModifyResult commentModifyResult = CommentResultFactory.newModifyComment(modifyComment);
        return Response.success(commentModifyResult);

    }

}
