package com.likelion.likelioncrud.post.api;

import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.post.api.dto.request.PostSaveRequestDto;
import com.likelion.likelioncrud.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.likelioncrud.post.api.dto.response.PostListResponseDto;
import com.likelion.likelioncrud.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 게시물 저장
    @PostMapping("/save")
    public ResponseEntity<ApiResTemplate<Void>> postSave(@RequestBody @Valid PostSaveRequestDto postSaveRequestDto) {
        postService.postSave(postSaveRequestDto);
        return ResponseEntity
                .status(SuccessCode.POST_SAVE_SUCCESS.getHttpStatus())
                .body(ApiResTemplate.successResponse(SuccessCode.POST_SAVE_SUCCESS, null));
    }

    // 사용자 id를 기준으로 해당 사용자가 작성한 게시글 목록 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResTemplate<PostListResponseDto>> myPostFindAll(@PathVariable("memberId") Long memberId) {
        PostListResponseDto postListResponseDto = postService.postFindMember(memberId);
        return ResponseEntity
                .ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, postListResponseDto));
    }

    // 게시물 id를 기준으로 사용자가 작성한 게시물 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResTemplate<Void>> postUpdate(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        postService.postUpdate(postId, postUpdateRequestDto);
        return ResponseEntity
                .ok(ApiResTemplate.successResponse(SuccessCode.POST_UPDATE_SUCCESS, null));
    }

    // 게시물 id를 기준으로 사용자가 작성한 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResTemplate<Void>> postDelete(
            @PathVariable("postId") Long postId) {
        postService.postDelete(postId);
        return ResponseEntity
                .ok(ApiResTemplate.successResponse(SuccessCode.POST_DELETE_SUCCESS, null));
    }
}
