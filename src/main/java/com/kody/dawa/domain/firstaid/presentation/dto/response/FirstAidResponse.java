package com.kody.dawa.domain.firstaid.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder(toBuilder = true)
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

    private List<RelatedFirstAidResponse> relatedFirstAids;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelatedFirstAidResponse {
        private String title;
        private List<TagResponse> tags;
        private Long firstAidId;
    }
}
