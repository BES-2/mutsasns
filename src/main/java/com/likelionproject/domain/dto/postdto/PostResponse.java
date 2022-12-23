package com.likelionproject.domain.dto.postdto;

import com.likelionproject.domain.dto.result.PostCreateResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostResponse<T> {
    private String resultCode;
    private T t;
}
