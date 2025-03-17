package com.kody.dawa.domain.firstaid.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirstAidRequest {
    private String content;
    private String title;
    private String emoji;
    private String diseaseName;
    private List<TagRequest> tags;
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagRequest {
        private String name;
    }
}
