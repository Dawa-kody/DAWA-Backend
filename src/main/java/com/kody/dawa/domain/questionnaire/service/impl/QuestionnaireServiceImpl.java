package com.kody.dawa.domain.questionnaire.service.impl;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentRecordResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentSearchResponse;
import com.kody.dawa.domain.questionnaire.repository.QuestionnaireRepository;
import com.kody.dawa.domain.questionnaire.service.QuestionnaireService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

        if (yearMonthDay == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            yearMonthDay = LocalDate.now().format(formatter);
        }
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

    public List<StudentSearchResponse> searchStudent(String userName, String schoolNumber) {
        List<User> users = userRepository.findByNameOrSchoolNumber(userName, schoolNumber);
        List<StudentSearchResponse> responses = new ArrayList<>();

        for (User user : users) {
            Long userId = user.getId();
            List<Questionnaire> questionnaires = questionnaireRepository.findByUserId(userId);

            Optional<Questionnaire> latestQuestionnaire = questionnaires.stream()
                    .max(Comparator.comparing(Questionnaire::getYearMonthDay));

            latestQuestionnaire.ifPresent(questionnaire -> {
                responses.add(StudentSearchResponse.builder()
                        .userName(questionnaire.getUserName())
                        .yearMonthDay(questionnaire.getYearMonthDay())
                        .schoolNumber(questionnaire.getSchoolNumber())
                        .userId(questionnaire.getUser().getId())
                        .build());
            });
        }

        return responses;
    }

    public List<StudentRecordResponse> recordStudent(Long id) {
        List<Questionnaire> questionnaires = questionnaireRepository.findByUserId(id);

        return questionnaires.stream()
                .map(response -> StudentRecordResponse.builder()
                        .schoolNumber(response.getSchoolNumber())
                        .disease(response.getDisease())
                        .content(response.getContent())
                        .yearMonthDay(response.getYearMonthDay())
                        .build())
                .collect(Collectors.toList());
    }
}
