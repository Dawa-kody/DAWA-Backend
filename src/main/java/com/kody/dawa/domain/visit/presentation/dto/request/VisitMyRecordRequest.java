package com.kody.dawa.domain.visit.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitMyRecordRequest {
    private String content;
}
