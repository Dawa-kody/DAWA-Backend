package com.kody.dawa.domain.questionnaire.service.impl;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.repository.QuestionnaireRepository;
import com.kody.dawa.domain.questionnaire.service.QuestionnaireService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private final UserRepository userRepository;
    public List<Questionnaire> createQuestionnaires(List<QuestionnaireRequest> requests) {
        List<Questionnaire> questionnaires = requests.stream()
                .map(request -> {
                    User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                            .orElseThrow(() -> new RuntimeException("없는 유저입니다 : " + request.getSchoolNumber()));

                    return Questionnaire.builder()
                            .serialNumber(request.getSerialNumber())
                            .userName(request.getUserName())
                            .user(user)
                            .schoolNumber(request.getSchoolNumber())
                            .disease(request.getDisease())
                            .content(request.getContent())
                            .time(request.getTime())
                            .gender(request.getGender())
                            .build();
                })
                .toList();

        return questionnaireRepository.saveAll(questionnaires);
    }

    public List<QuestionnaireResponse> getQuestionnairesByYearMonthDay(String yearMonthDay) {
        List<Questionnaire> questionnaires = questionnaireRepository.findByYearMonthDay(yearMonthDay);

        return questionnaires.stream()
                .map(response -> QuestionnaireResponse.builder()
                        .serialNumber(response.getSerialNumber())
                        .userName(response.getUserName())
                        .schoolNumber(response.getSchoolNumber())
                        .disease(response.getDisease())
                        .content(response.getContent())
                        .time(response.getTime())
                        .gender(response.getGender())
                        .build())
                .collect(Collectors.toList());
    }
}
