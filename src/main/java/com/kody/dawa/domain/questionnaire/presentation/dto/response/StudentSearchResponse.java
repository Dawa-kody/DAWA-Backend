package com.kody.dawa.domain.questionnaire.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StudentSearchResponse {
    private String yearMonthDay;
    private String schoolNumber;
    private String userName;
    private Long userId;
}
