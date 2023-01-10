package com.likelionproject.domain.dto.postdto.result;

import com.likelionproject.domain.dto.myfeeddto.MyFeedResult;
import com.likelionproject.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class PostResultFactory {
    public static PostModifyResult from(Long postId) {
        return new PostModifyResult("포스트 수정 완료", postId);
    }

    public static PostCreateResult newCreateResult(Post post) {
        return new PostCreateResult(
                "포스트 등록 완료",
                post.getId()
        );
    }

    public static PostGetResult from(Post post) {
        return PostGetResult.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUser().getUserName())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getCreatedAt()))
                .lastModifiedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getLastModifiedAt()))
                .build();
    }

    public static PostDeleteResult newResult(Long deleteId) {
        return new PostDeleteResult("포스트 삭제 완료", deleteId);
    }

    public static Page<PostGetResult> newPage(Page<Post> posts) {
        return posts.map(
                post -> PostGetResult.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .body(post.getBody())
                        .userName(post.getUser().getUserName())
                        .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getCreatedAt()))
                        .lastModifiedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getLastModifiedAt()))
                        .build()
        );
    }

    public static MyFeedResult newMyFeed(Page<PostGetResult> postAllResult, Pageable pageable) {
        return MyFeedResult.builder()
                .content(postAllResult.getContent())
                .pageable(pageable)
                .numberOfElements(postAllResult.getNumberOfElements())
                .empty(postAllResult.isEmpty())
                .build();
    }

    public static PostPageResult from(Page<PostGetResult> postAllResult) {
        return PostPageResult.builder()
                .content(postAllResult.getContent())
                .pageable("INSTANCE")
                .last(postAllResult.hasNext())
                .totalElements(postAllResult.getTotalElements())
                .totalPages(postAllResult.getTotalPages())
                .size(postAllResult.getSize())
                .number(postAllResult.getNumber())
                .sort(postAllResult.getSort())
                .first(postAllResult.isFirst())
                .numberOfElements(postAllResult.getNumberOfElements())
                .empty(postAllResult.isEmpty())
                .build();
    }

}
