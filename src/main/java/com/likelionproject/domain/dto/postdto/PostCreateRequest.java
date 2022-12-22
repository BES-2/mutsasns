package com.likelionproject.domain.dto.postdto;

import com.likelionproject.domain.Post;
import com.likelionproject.domain.User;
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
