package com.likelionproject.service;

import com.likelionproject.domain.dto.myfeeddto.MyFeedResult;
import com.likelionproject.domain.entity.Post;
import com.likelionproject.domain.dto.postdto.result.*;
import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.postdto.request.PostCreateRequest;
import com.likelionproject.domain.dto.postdto.request.PostModifyRequest;
import com.likelionproject.domain.entity.User;
import com.likelionproject.exception.AppException;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.repository.PostRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /*  포스트 수정  */
    public Response<PostModifyResult> modifyPost(Long postId, PostModifyRequest postModifyRequest, String modifierUserName) {
        Long modifierUserId = userRepository.findByUserName(modifierUserName).get().getId();

        Post selectPost = postRepository.findById(postId)
                .filter(post -> post.getUser().getId().equals(modifierUserId))
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));

        selectPost.modifyPost(postModifyRequest);

        PostModifyResult postModifyResult = PostResultFactory.from(postId);
        return Response.success(postModifyResult);
    }

    /*     새 포스트 작성    */
    public Response<PostCreateResult> createPost(PostCreateRequest postCreateRequest, Authentication authentication) {
        Post newPost = postRepository
                .save(postCreateRequest.toEntity(userRepository.findByUserName(authentication.getName()).get()));

        PostCreateResult postCreateResult = PostResultFactory.newCreateResult(newPost);
        return Response.success(postCreateResult);
    }


    @Transactional(readOnly = true)
    public Response<PostGetResult> getOnePost(Long postId) {
        Post getPost = postRepository.findById(postId).orElseThrow(() -> new AppException((ErrorCode.POST_NOT_FOUNDED)));
        PostGetResult postGetResult = PostResultFactory.from(getPost);
        return Response.success(postGetResult);
    }

    public Response<PostDeleteResult> deletePost(Long deleteId, String deleteUserName) {
        Long deleteUserId = userRepository.findByUserName(deleteUserName).get().getId();

        Post selectedPost = postRepository.findById(deleteId)
                .filter(post -> post.getUser().getId().equals(deleteUserId))
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION));

        postRepository.delete(selectedPost);
        PostDeleteResult postDeleteResult = PostResultFactory.newResult(deleteId);
        return Response.success(postDeleteResult);
    }

    @Transactional(readOnly = true)
    public Response<PostPageResult> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostGetResult> postAllResult = PostResultFactory.newPage(posts);

        PostPageResult postPageResult = PostResultFactory.from(postAllResult);

        return Response.success(postPageResult);
    }

    public Response<MyFeedResult> getMyFeed(String userName, Pageable pageable) {
        User selectedUser = userRepository.findByUserName(userName).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));
        Page<Post> myPosts = postRepository.findByUserId(selectedUser.getId(), pageable);
        Page<PostGetResult> myPostsResult = PostResultFactory.newPage(myPosts);

        MyFeedResult myFeedResult = PostResultFactory.newMyFeed(myPostsResult);

        return Response.success(myFeedResult);
    }

}
