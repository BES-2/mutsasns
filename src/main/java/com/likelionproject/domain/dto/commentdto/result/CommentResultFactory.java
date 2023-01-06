package com.likelionproject.domain.dto.commentdto.result;

import com.likelionproject.domain.dto.postdto.result.PostGetResult;
import com.likelionproject.domain.dto.postdto.result.PostPageResult;
import com.likelionproject.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class CommentResultFactory {

    public static CommentCreateResult from(Comment comment) {
        return CommentCreateResult.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt()))
                .build();
    }

    public static CommentModifyResult newModifyCommentResult(Comment comment) {
        return CommentModifyResult.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt()))
                .lastModifiedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getLastModifiedAt()))
                .build();
    }

    public static CommentDeleteResult newDeleteComment(Long commentId) {
        return new CommentDeleteResult(
                "댓글 삭제 완료",
                commentId
        );
    }

    public static Page<CommentGetResult> newPage(Page<Comment> comments) {
        return comments.map(
                comment -> CommentGetResult.builder()
                        .id(comment.getId())
                        .comment(comment.getComment())
                        .userName(comment.getUser().getUserName())
                        .postId(comment.getPost().getId())
                        .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt()))
                        .build()
        );
    }

    // TODO: Post, Comment Page 정보 출력 부분 Class 로 분리
    public static CommentPageResult from(Page<CommentGetResult> commentGetResults) {
        return CommentPageResult.builder()
                .content(commentGetResults.getContent())
                .pageable("INSTANCE")
                .last(commentGetResults.hasNext())
                .totalElements(commentGetResults.getTotalElements())
                .totalPages(commentGetResults.getTotalPages())
                .size(commentGetResults.getSize())
                .number(commentGetResults.getNumber())
                .sort(commentGetResults.getSort())
                .first(commentGetResults.isFirst())
                .numberOfElements(commentGetResults.getNumberOfElements())
                .empty(commentGetResults.isEmpty())
                .build();
    }

}
