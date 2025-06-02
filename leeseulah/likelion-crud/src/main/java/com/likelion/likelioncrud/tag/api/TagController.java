package com.likelion.likelioncrud.tag.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.application.TagService;
import com.likelion.likelioncrud.tag.domain.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    // 태그 저장
    @PostMapping
    public ApiResTemplate<String> tagSave(@RequestBody @Valid TagSaveRequestDto tagSaveRequestDto) {
        tagService.save(tagSaveRequestDto);
        return ApiResTemplate.successResponse(SuccessCode.TAG_SAVE_SUCCESS, SuccessCode.TAG_SAVE_SUCCESS.getMessage());
    }

    // 태그 전체 조회
    @GetMapping
    public ApiResTemplate<TagListResponseDto> findAllTags() {
        TagListResponseDto tagListResponseDto = tagService.findAll();
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagListResponseDto);
    }

    // 태그 단건 조회
    @GetMapping("/{id}")
    public ApiResTemplate<TagInfoResponseDto> findTagById(@PathVariable("id") Long id) {
        Tag tag = tagService.findOne(id);
        TagInfoResponseDto dto = TagInfoResponseDto.from(tag);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, dto);
    }

    // 태그 수정
    @PatchMapping("/{id}")
    public ApiResTemplate<String> tagUpdate(@PathVariable("id") Long id,
                                            @RequestBody TagUpdateRequestDto tagUpdateRequestDto) {
        tagService.update(id, tagUpdateRequestDto);
        return ApiResTemplate.successResponse(SuccessCode.TAG_UPDATE_SUCCESS, SuccessCode.TAG_UPDATE_SUCCESS.getMessage());
    }

    // 태그 삭제
    @DeleteMapping("/{id}")
    public ApiResTemplate<String> deleteTag(@PathVariable("id") Long id) {
        tagService.delete(id);
        return ApiResTemplate.successResponse(SuccessCode.TAG_DELETE_SUCCESS, SuccessCode.TAG_DELETE_SUCCESS.getMessage());
    }
}