package com.likelionproject.service;

import com.likelionproject.domain.Post;
import com.likelionproject.domain.dto.PostDto;
import com.likelionproject.domain.dto.postdto.PostCreateRequest;
import com.likelionproject.domain.dto.postdto.PostCreateResponse;
import com.likelionproject.domain.dto.result.PostCreateResult;
import com.likelionproject.repository.PostRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostCreateResponse CreatePost(PostCreateRequest postCreateRequest, Authentication authentication) {
        Post newPost = postRepository
                .save(postCreateRequest.toEntity(userRepository.findByUserName(authentication.getName()).get()));

        PostDto postDto = PostDto.builder()
                .id(newPost.getId())
                .title(newPost.getTitle())
                .body(newPost.getBody())
                .build();

        PostCreateResult result = new PostCreateResult("포스트 등록 완료", postDto.getId());
        return new PostCreateResponse("SUCCESS", result);
    }
}
