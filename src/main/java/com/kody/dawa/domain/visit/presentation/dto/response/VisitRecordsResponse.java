package com.kody.dawa.domain.visit.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class VisitRecordsResponse {
    private String name;
    private String content;
    private String formattedDate;
    private String dayOfWeek;
}
