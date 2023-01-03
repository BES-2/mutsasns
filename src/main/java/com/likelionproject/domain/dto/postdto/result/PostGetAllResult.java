package com.likelionproject.domain.dto.postdto.result;

import com.likelionproject.domain.dto.postdto.result.PostGetResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostGetAllResult {
    private Page<PostGetResult> result;
}
