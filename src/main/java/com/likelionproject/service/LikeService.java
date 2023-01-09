package com.likelionproject.service;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.entity.Like;
import com.likelionproject.domain.entity.Post;
import com.likelionproject.domain.entity.User;
import com.likelionproject.exception.AppException;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.repository.LikeRepository;
import com.likelionproject.repository.PostRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public Response<String> makeLike(Long postId, String userName) {
        Post selectedPost = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUNDED));
        User selectedUser = userRepository.findByUserName(userName).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));

        if(likeRepository.findByUserIdAndPostId(selectedUser.getId(), postId).isEmpty()){
            likeRepository.save(new Like(selectedUser, selectedPost));
            return Response.success("좋아요를 눌렀습니다.");
        }

        Like selectedLike = likeRepository.findByUserIdAndPostId(selectedUser.getId(), postId).get();

        if(selectedLike.getDeletedAt() == null) {
            selectedLike.addDeletedAtLike();
            return Response.success("좋아요를 취소했습니다.");
        }
        selectedLike.setDeleteAtNullLike();
        return Response.success("좋아요를 눌렀습니다.");

    }
}
