package com.kody.dawa.domain.notice.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeWriteRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
