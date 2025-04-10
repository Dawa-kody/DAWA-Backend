package com.kody.dawa.domain.questionnaire.repository;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    @Query("SELECT q FROM Questionnaire q WHERE q.yearMonthDay = :yearMonthDay ORDER BY q.serialNumber ASC")
    List<Questionnaire> findByYearMonthDayOrderBySerialNumber(@Param("yearMonthDay") String yearMonthDay);

    List<Questionnaire> findByUserId(Long userId);
}
