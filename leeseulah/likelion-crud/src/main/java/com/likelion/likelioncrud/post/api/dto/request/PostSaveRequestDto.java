package com.likelion.likelioncrud.post.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostSaveRequestDto(
        @NotNull(message = "작성자는 필수로 입력하셔야 합니다.")
        Long memberId,

        @NotBlank(message = "제목은 필수로 입력해야 합니다.")
        @Size(min = 2, max = 10, message = "제목은 2자 이상 10자 이하로 입력해야 합니다.")
        String title,

        @NotBlank(message = "내용은 필수로 입력하셔야 합니다.")
        @Size(min = 2, max = 10, message = "내용은 2자 이상 10자 이하로 입력해야 합니다.")
        String contents,

        List<String> tagIds
) {
}
