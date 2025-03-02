package com.kody.dawa.domain.questionnaire.service;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;

import java.util.List;

public interface QuestionnaireService {
    List<Questionnaire> createQuestionnaires(List<QuestionnaireRequest> requests);
}
