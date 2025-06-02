package com.likelion.likelioncrud.posttag.domain.repository;

import com.likelion.likelioncrud.post.domain.Post;
import com.likelion.likelioncrud.posttag.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    void deleteByPost(Post post);

    List<PostTag> findByPost(Post post);
}
