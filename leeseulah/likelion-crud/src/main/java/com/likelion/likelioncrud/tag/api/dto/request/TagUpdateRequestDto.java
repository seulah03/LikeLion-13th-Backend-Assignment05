package com.likelion.likelioncrud.tag.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TagUpdateRequestDto(
        @NotBlank(message = "태그 이름은 필수입니다.")
        String name
) {}
