package com.likelionproject.domain.dto.myfeeddto;

import com.likelionproject.domain.dto.postdto.result.PostGetResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyFeedResult {
    private List<PostGetResult> content;

    private Pageable pageable;
    private Integer numberOfElements;
    private Boolean first;
    private Boolean empty;
}
