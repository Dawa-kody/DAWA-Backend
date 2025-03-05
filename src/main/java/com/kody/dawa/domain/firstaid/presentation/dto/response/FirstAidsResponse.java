package com.kody.dawa.domain.firstaid.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FirstAidsResponse {
    private String title;
    private String emoji;
    private Long firstAidId;
    private List<TagResponse> tags;
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagResponse {
        private String name;
    }
}
