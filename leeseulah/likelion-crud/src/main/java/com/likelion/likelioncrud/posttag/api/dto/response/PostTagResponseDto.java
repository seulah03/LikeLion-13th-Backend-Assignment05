package com.likelion.likelioncrud.posttag.api.dto.response;

import com.likelion.likelioncrud.posttag.domain.PostTag;

public record PostTagResponseDto(
        Long postId,
        Long tagId
) {
    public static PostTagResponseDto from(PostTag postTag) {
        return new PostTagResponseDto(
                postTag.getPost().getPostId(),
                postTag.getTag().getId()
        );
    }
}
