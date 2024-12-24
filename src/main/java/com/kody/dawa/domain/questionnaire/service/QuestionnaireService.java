package com.kody.dawa.domain.questionnaire.service;

import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;

public interface QuestionnaireService {
    void createQuestionnaire(QuestionnaireRequest request);
}
