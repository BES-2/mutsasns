package com.likelionproject.service;

import com.likelionproject.domain.Post;
import com.likelionproject.domain.User;
import com.likelionproject.domain.dto.PostDto;
import com.likelionproject.domain.dto.postdto.DeleteResult;
import com.likelionproject.domain.dto.postdto.PostCreateRequest;
import com.likelionproject.domain.dto.postdto.PostModifyRequest;
import com.likelionproject.domain.dto.postdto.PostResponse;
import com.likelionproject.domain.dto.result.PostCreateResult;
import com.likelionproject.domain.dto.result.PostGetResult;
import com.likelionproject.domain.dto.result.PostModifyResult;
import com.likelionproject.exception.AppException;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.repository.PostRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //TODO: CreatePost, ModifyPost 에서 다른 token일 경우 에러처리

    /*----- 게시글 수정 -----*/
    public PostResponse modifyPost(Long postId, PostModifyRequest postModifyRequest, String modifierUserName) {
        Long modifierUserId = userRepository.findByUserName(modifierUserName).get().getId();

        Post selectPost = postRepository.findById(postId)
                .filter(post -> post.getUser().getId() == modifierUserId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));

        selectPost.modifyPost(postModifyRequest);

        PostModifyResult postModifyResult = new PostModifyResult("포스트 수정 완료", postId);

        return new PostResponse("SUCCESS", postModifyResult);
    }

    public PostResponse createPost(PostCreateRequest postCreateRequest, Authentication authentication) {
        Post newPost = postRepository
                .save(postCreateRequest.toEntity(userRepository.findByUserName(authentication.getName()).get()));

        PostDto postDto = PostDto.builder()
                .id(newPost.getId())
                .title(newPost.getTitle())
                .body(newPost.getBody())
                .build();

        PostCreateResult result = new PostCreateResult("포스트 등록 완료", postDto.getId());
        return new PostResponse("SUCCESS", result);
    }

    @Transactional(readOnly = true)
    public PostResponse getOnePost(Long postId) {
        Post getPost = postRepository.findById(postId).orElseThrow(() -> new AppException((ErrorCode.POST_NOT_FOUND)));
        PostGetResult postGetResult = PostGetResult.builder()
                .id(postId)
                .title(getPost.getTitle())
                .body(getPost.getBody())
                .userName(getPost.getUser().getUserName())
                .createdAt(getPost.getCreatedAt())
                .lastModifiedAt(getPost.getLastModifiedAt())
                .build();
        return new PostResponse("SUCCESS", postGetResult);
    }

    public PostResponse deletePost(Long deleteId, String deleteUserName) {
        Long deleteUserId = userRepository.findByUserName(deleteUserName).get().getId();

        Post selectedPost = postRepository.findById(deleteId)
                .filter(post -> post.getUser().getId() == deleteUserId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));

        postRepository.delete(selectedPost);
        DeleteResult deleteResult = new DeleteResult("포스트 삭제 완료", deleteId);
        return new PostResponse("SUCCESS", deleteResult);
    }
}
