package com.likelion.likelioncrud.tag.api;

import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.application.TagService;
import com.likelion.likelioncrud.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagInfoResponseDto> save(@RequestBody TagSaveRequestDto dto) {
        return ResponseEntity.ok(tagService.save(dto));
    }

    @GetMapping
    public ResponseEntity<TagListResponseDto> findAll() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagInfoResponseDto> findOne(@PathVariable("id") Long id) {
        Tag tag = tagService.findOne(id);
        TagInfoResponseDto dto = TagInfoResponseDto.from(tag);
        return ResponseEntity.ok(dto);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<TagInfoResponseDto> update(@PathVariable("id") Long id, @RequestBody TagUpdateRequestDto dto) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
