package com.kody.dawa.domain.questionnaire.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireRequest {
    private String content;
    private String schoolNumber;
    private String result;
}
