package com.likelionproject.domain.dto.postdto;

import com.likelionproject.domain.dto.result.PostCreateResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCreateResponse {
    private String resultCode;
    private PostCreateResult postCreateResult;
}
