package com.likelion.likelioncrud.tag.application;

import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.domain.Tag;
import com.likelion.likelioncrud.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public TagInfoResponseDto save(TagSaveRequestDto dto) {
        Tag tag = Tag.builder().name(dto.name()).build();
        return TagInfoResponseDto.from(tagRepository.save(tag));
    }

    @Transactional(readOnly = true)
    public TagListResponseDto findAll() {
        List<TagInfoResponseDto> tagList = tagRepository.findAll().stream()
                .map(TagInfoResponseDto::from)
                .toList();
        return TagListResponseDto.from(tagList);
    }

    public Tag findOne(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 없습니다. id=" + id));
    }


    @Transactional
    public TagInfoResponseDto update(Long id, TagUpdateRequestDto dto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found"));
        tag.update(dto);
        return TagInfoResponseDto.from(tag);
    }

    @Transactional
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
