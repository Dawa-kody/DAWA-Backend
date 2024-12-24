package com.kody.dawa.domain.questionnaire.service.impl;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.repository.QuestionnaireRepository;
import com.kody.dawa.domain.questionnaire.service.QuestionnaireService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private final UserRepository userRepository;
    public void createQuestionnaire(QuestionnaireRequest request) {
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("잘못된 학번 입니다."));
        Questionnaire questionnaire = Questionnaire.builder()
                .content(request.getContent())
                .user(user)
                .result(request.getResult())
                .build();
        questionnaireRepository.save(questionnaire);
    }
}
