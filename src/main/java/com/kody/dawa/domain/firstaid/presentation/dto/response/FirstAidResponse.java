package com.kody.dawa.domain.firstaid.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FirstAidResponse {
    private String title;
    private String emoji;
    private String content;
    private List<TagResponse> tags;
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagResponse {
        private String name;
    }
}
