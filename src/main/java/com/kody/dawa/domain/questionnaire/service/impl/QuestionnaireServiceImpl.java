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
    public void createQuestionnaire(QuestionnaireRequest request) {
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("없는 유저입니다 : " + request.getSchoolNumber()));

        Questionnaire questionnaire = Questionnaire.builder()
                .serialNumber(request.getSerialNumber())
                .userName(request.getUserName())
                .user(user)
                .schoolNumber(request.getSchoolNumber())
                .disease(request.getDisease())
                .gender(request.getGender())
                .division(request.getDivision())
                .medication1(request.getMedication1())
                .medication2(request.getMedication2())
                .quantity(request.getQuantity())
                .quantity1(request.getQuantity1())
                .quantity2(request.getQuantity2())
                .treatment(request.getTreatment())
                .notes(request.getNotes())
                .build();

        questionnaireRepository.save(questionnaire);
    }

    public List<QuestionnaireResponse> getQuestionnairesByYearMonthDay(String yearMonthDay) {

        if (yearMonthDay == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            yearMonthDay = LocalDate.now().format(formatter);
        }
        List<Questionnaire> questionnaires = questionnaireRepository.findByYearMonthDayOrderBySerialNumber(yearMonthDay);

        return questionnaires.stream()
                .map(response -> QuestionnaireResponse.builder()
                        .serialNumber(response.getSerialNumber())
                        .userName(response.getUserName())
                        .schoolNumber(response.getSchoolNumber())
                        .disease(response.getDisease())
                        .gender(response.getGender())
                        .division(response.getDivision())
                        .medication1(response.getMedication1())
                        .medication2(response.getMedication2())
                        .quantity(response.getQuantity())
                        .quantity1(response.getQuantity1())
                        .quantity2(response.getQuantity2())
                        .treatment(response.getTreatment())
                        .notes(response.getNotes())
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
                        .content(response.getTreatment())
                        .yearMonthDay(response.getYearMonthDay())
                        .build())
                .collect(Collectors.toList());
    }
}
