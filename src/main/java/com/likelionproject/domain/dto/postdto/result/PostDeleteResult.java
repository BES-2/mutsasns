package com.likelionproject.domain.dto.postdto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostDeleteResult {
    private String message;
    private Long postId;
}
