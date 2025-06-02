package com.likelion.likelioncrud.tag.domain.repository;

import com.likelion.likelioncrud.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name); // 태그 이름으로 중복 확인 및 조회
}
