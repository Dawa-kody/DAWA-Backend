package com.kody.dawa.domain.questionnaire.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class QuestionnaireStatisticsResponse {
    private List<QuestionnaireResponse> questionnaires;
    private Map<String, Map<String, Map<String, Integer>>> groupedStatistics;
}
