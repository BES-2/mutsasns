package com.likelionproject.domain.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteResult {
    private String message;
    private Long postId;
}
