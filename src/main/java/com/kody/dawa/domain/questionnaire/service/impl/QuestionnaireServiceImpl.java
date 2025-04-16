package com.kody.dawa.domain.questionnaire.service.impl;

import com.kody.dawa.domain.medicine.entity.Medicine;
import com.kody.dawa.domain.medicine.repository.MedicineRepository;
import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.entity.Statistics;
import com.kody.dawa.domain.questionnaire.enums.Division;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireDeleteRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentRecordResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentSearchResponse;
import com.kody.dawa.domain.questionnaire.repository.QuestionnaireRepository;
import com.kody.dawa.domain.questionnaire.repository.StatisticsRepository;
import com.kody.dawa.domain.questionnaire.service.QuestionnaireService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.enums.Gender;
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
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final StatisticsRepository statisticsRepository;
    public void createQuestionnaire(QuestionnaireRequest request) {
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("없는 유저입니다 : " + request.getSchoolNumber()));

        if(request.getQuantity() != null) {
            Medicine treatment = medicineRepository.findByName(request.getTreatment())
                    .orElseThrow(() -> new RuntimeException("해당 약이 존재하지 않습니다: " + request.getTreatment()));

            if (treatment.getCount() < request.getQuantity()) {
                throw new RuntimeException("약 수량이 부족합니다: " + request.getTreatment());
            }

            treatment.setCount(treatment.getCount() - request.getQuantity());
            medicineRepository.save(treatment);

            if (request.getMedication1() != null && !request.getMedication1().trim().isEmpty()) {
                Medicine medicine1 = medicineRepository.findByName(request.getMedication1())
                        .orElseThrow(() -> new RuntimeException("해당 약이 존재하지 않습니다: " + request.getMedication1()));

                if (medicine1.getCount() < request.getQuantity1()) {
                    throw new RuntimeException("약 수량이 부족합니다: " + request.getMedication1());
                }

                medicine1.setCount(medicine1.getCount() - request.getQuantity1());
                medicineRepository.save(medicine1);
            }

            if (request.getMedication2() != null && !request.getMedication2().trim().isEmpty()) {
                Medicine medicine2 = medicineRepository.findByName(request.getMedication2())
                        .orElseThrow(() -> new RuntimeException("해당 약이 존재하지 않습니다: " + request.getMedication2()));

                if (medicine2.getCount() < request.getQuantity2()) {
                    throw new RuntimeException("약 수량이 부족합니다: " + request.getMedication2());
                }

                medicine2.setCount(medicine2.getCount() - request.getQuantity2());
                medicineRepository.save(medicine2);
            }
        }

        Questionnaire questionnaire = Questionnaire.builder()
                .serialNumber(request.getSerialNumber())
                .user(user)
                .disease(request.getDisease())
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

        updateStatistics(questionnaire);
    }

    private void updateStatistics(Questionnaire questionnaire) {
        String day = questionnaire.getYearMonthDay();
        String month = day.substring(0, 7);
        String year = day.substring(0, 4);

        Gender gender = questionnaire.getUser().getGender();
        Division division = questionnaire.getDivision();

        updateStat("DAILY", day, gender, division);
        updateStat("MONTHLY", month, gender, division);
        updateStat("YEARLY", year, gender, division);
    }

    private void updateStat(String type, String date, Gender gender, Division division) {
        Statistics stat = statisticsRepository.findByDateTypeAndDateAndGenderAndDivision(type, date, gender, division)
                .orElse(Statistics.builder()
                        .dateType(type)
                        .date(date)
                        .gender(gender)
                        .division(division)
                        .count(0L)
                        .build());

        stat.setCount(stat.getCount() + 1);
        statisticsRepository.save(stat);
    }


    public void deleteQuestionnaire(QuestionnaireDeleteRequest request) {

        Questionnaire questionnaire = questionnaireRepository.findById(request.getQuestionnaireId())
                    .orElseThrow(() -> new RuntimeException("해당 문진표가 존재하지 않습니다: " + request.getQuestionnaireId()));

        if(request.getQuantity() != null) {
            Medicine treatment = medicineRepository.findByName(request.getTreatment())
                    .orElseThrow(() -> new RuntimeException("해당 약이 존재하지 않습니다: " + request.getTreatment()));

            treatment.setCount(treatment.getCount() + request.getQuantity());
            medicineRepository.save(treatment);

            if (request.getMedication1() != null && !request.getMedication1().trim().isEmpty()) {
                Medicine medicine1 = medicineRepository.findByName(request.getMedication1())
                        .orElseThrow(() -> new RuntimeException("해당 약이 존재하지 않습니다: " + request.getMedication1()));

                medicine1.setCount(medicine1.getCount() + request.getQuantity1());
                medicineRepository.save(medicine1);
            }

            if (request.getMedication2() != null && !request.getMedication2().trim().isEmpty()) {
                Medicine medicine2 = medicineRepository.findByName(request.getMedication2())
                        .orElseThrow(() -> new RuntimeException("해당 약이 존재하지 않습니다: " + request.getMedication2()));

                medicine2.setCount(medicine2.getCount() + request.getQuantity2());
                medicineRepository.save(medicine2);
            }
        }

        updateStatisticsOnDelete(questionnaire);

        questionnaireRepository.delete(questionnaire);
    }


    private void updateStatisticsOnDelete(Questionnaire questionnaire) {
        String day = questionnaire.getYearMonthDay();
        String month = day.substring(0, 7);
        String year = day.substring(0, 4);

        Gender gender = questionnaire.getUser().getGender();
        Division division = questionnaire.getDivision();

        decrementStat("DAILY", day, gender, division);
        decrementStat("MONTHLY", month, gender, division);
        decrementStat("YEARLY", year, gender, division);
    }

    private void decrementStat(String type, String date, Gender gender, Division division) {
        Statistics stat = statisticsRepository.findByDateTypeAndDateAndGenderAndDivision(type, date, gender, division)
                .orElseThrow(() -> new RuntimeException(type + " 통계가 존재하지 않습니다: " + date));

        if (stat.getCount() > 0) {
            stat.setCount(stat.getCount() - 1);
            statisticsRepository.save(stat);
        } else {
            throw new RuntimeException(type + " 통계 수치가 0보다 적을 수 없습니다: " + date);
        }
    }

    public List<QuestionnaireResponse> getQuestionnairesByYearMonthDay(String yearMonthDay) {

        if (yearMonthDay == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            yearMonthDay = LocalDate.now().format(formatter);
        }
        List<Questionnaire> questionnaires = questionnaireRepository.findByYearMonthDayOrderBySerialNumber(yearMonthDay);

        return questionnaires.stream()
                .map(response -> QuestionnaireResponse.builder()
                        .questionnaireId(response.getId())
                        .serialNumber(response.getSerialNumber())
                        .userName(response.getUser().getName())
                        .schoolNumber(response.getUser().getSchoolNumber())
                        .disease(response.getDisease())
                        .gender(String.valueOf(response.getUser().getGender()))
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
                        .userName(questionnaire.getUser().getName())
                        .yearMonthDay(questionnaire.getYearMonthDay())
                        .schoolNumber(questionnaire.getUser().getSchoolNumber())
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
                        .schoolNumber(response.getUser().getSchoolNumber())
                        .disease(response.getDisease())
                        .content(response.getTreatment())
                        .yearMonthDay(response.getYearMonthDay())
                        .build())
                .collect(Collectors.toList());
    }
}
