package com.likelionproject.domain.dto.postdto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostCreateResult {
    private String message;
    private Long postId;
}
