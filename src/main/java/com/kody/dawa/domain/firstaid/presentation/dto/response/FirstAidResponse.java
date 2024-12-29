package com.kody.dawa.domain.firstaid.presentation.dto.response;

import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
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
    private List<TagResponse> tags;
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagResponse {
        private String name;
    }
}
