package com.likelionproject.repository;

import com.likelionproject.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    @Query(value="SELECT comment from Comment comment where comment.post.id= :post_id")
//    Page<Comment> findByPostId(@Param("post_id") Long postId);
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
