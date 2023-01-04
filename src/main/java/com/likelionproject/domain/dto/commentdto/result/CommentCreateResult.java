package com.likelionproject.domain.dto.commentdto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentCreateResult {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;
    private String createdAt;
}
