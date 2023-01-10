package com.likelionproject.service;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.commentdto.request.CommentCreateRequest;
import com.likelionproject.domain.dto.commentdto.request.CommentModifyRequest;
import com.likelionproject.domain.dto.commentdto.result.*;
import com.likelionproject.domain.entity.Alarm;
import com.likelionproject.domain.entity.Comment;
import com.likelionproject.domain.entity.Post;
import com.likelionproject.domain.entity.User;
import com.likelionproject.exception.AppException;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.repository.AlarmRepository;
import com.likelionproject.repository.CommentRepository;
import com.likelionproject.repository.PostRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final AlarmRepository alarmRepository;

    public Response<CommentCreateResult> createComment(Long postId, CommentCreateRequest commentCreateRequest, Authentication authentication) {
        Post selectedPost = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUNDED));
        User writingUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));

        Comment newComment = commentRepository.save(commentCreateRequest.toEntity(selectedPost, writingUser));
        CommentCreateResult commentCreateResult = CommentResultFactory.from(newComment);

        alarmRepository.save(new Alarm().toEntity("NEW_COMMENT_ON_POST", selectedPost.getUser(), postId, "new comment!"));

        return Response.success(commentCreateResult);
    }

    public Response<CommentModifyResult> modifyComment(Long commentId, CommentModifyRequest commentModifyRequest, Authentication authentication) {
        User modifyingUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));
        Comment modifyComment = commentRepository.findById(commentId)
                .filter(comment -> comment.getUser().getId().equals(modifyingUser.getId()))
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));

        modifyComment.modifyComment(commentModifyRequest);

        CommentModifyResult commentModifyResult = CommentResultFactory.newModifyCommentResult(modifyComment);
        return Response.success(commentModifyResult);
    }

    public Response<CommentDeleteResult> deleteComment(Long commentId, Authentication authentication) {
        User deletingUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));
        Comment deleteComment = commentRepository.findById(commentId)
                .filter(comment -> comment.getUser().getId().equals(deletingUser.getId()))
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));
        commentRepository.delete(deleteComment);

        CommentDeleteResult commentDeleteResult = CommentResultFactory.newDeleteComment(commentId);
        return Response.success(commentDeleteResult);
    }

    public Response<CommentPageResult> getComments(Long postId, Pageable pageable) {
        Page<Comment> getComments = commentRepository.findByPostId(postId, pageable);
        Page<CommentGetResult> commentAllResult = CommentResultFactory.newPage(getComments);

        CommentPageResult commentPageResult = CommentResultFactory.from(commentAllResult);
        return Response.success(commentPageResult);
    }

}
