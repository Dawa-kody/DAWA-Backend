package com.kody.dawa.domain.questionnaire.service;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireDeleteRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireStatisticsResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentRecordResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentSearchResponse;

import java.util.List;

public interface QuestionnaireService {
    void createQuestionnaire(QuestionnaireRequest request);

    void deleteQuestionnaire(QuestionnaireDeleteRequest request);

    QuestionnaireStatisticsResponse getQuestionnairesByYearMonthDay(String yearMonthDay);

    List<StudentRecordResponse> recordStudent(Long id);

    List<StudentSearchResponse> searchStudent(String userName, String schoolNumber);
}
