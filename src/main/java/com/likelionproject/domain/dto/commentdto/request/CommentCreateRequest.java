package com.likelionproject.domain.dto.commentdto.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.likelionproject.domain.entity.Comment;
import com.likelionproject.domain.entity.Post;
import com.likelionproject.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentCreateRequest {
    private String comment;

    public Comment toEntity(Post selectedPost, User writingUser) {
        return Comment.builder()
                .comment(this.getComment())
                .post(selectedPost)
                .user(writingUser)
                .build();
    }

}
