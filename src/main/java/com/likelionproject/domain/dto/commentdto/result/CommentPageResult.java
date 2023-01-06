package com.likelionproject.domain.dto.commentdto.result;

import com.likelionproject.domain.dto.postdto.result.PostGetResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentPageResult {
    private List<CommentGetResult> content;
    private String pageable;
    private Boolean last;
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer number;
    private Sort sort;
    private Boolean first;
    private Integer numberOfElements;
    private Boolean empty;

}
