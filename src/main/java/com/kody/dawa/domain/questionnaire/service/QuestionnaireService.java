package com.kody.dawa.domain.questionnaire.service;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentRecordResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentSearchResponse;

import java.util.List;

public interface QuestionnaireService {
    List<Questionnaire> createQuestionnaires(List<QuestionnaireRequest> requests);

    List<QuestionnaireResponse> getQuestionnairesByYearMonthDay(String yearMonthDay);

    List<StudentRecordResponse> recordStudent(Long id);

    List<StudentSearchResponse> searchStudent(String userName, String schoolNumber);
}
