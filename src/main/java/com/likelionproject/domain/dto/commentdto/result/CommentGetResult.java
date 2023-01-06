package com.likelionproject.domain.dto.commentdto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentGetResult {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;
    private String createdAt;
}
