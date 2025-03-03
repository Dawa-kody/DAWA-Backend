package com.kody.dawa.domain.questionnaire.repository;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findByYearMonthDay(String yearMonthDay);
    List<Questionnaire> findByUserId(Long userId);
}
