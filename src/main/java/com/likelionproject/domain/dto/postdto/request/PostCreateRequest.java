package com.likelionproject.domain.dto.postdto.request;

import com.likelionproject.domain.entity.Post;
import com.likelionproject.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCreateRequest {
    private String title;
    private String body;

    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
                .title(this.getTitle())
                .body(this.getBody())
                .build();
    }
}
