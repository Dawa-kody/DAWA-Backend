package com.kody.dawa.domain.questionnaire.repository;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.entity.Statistics;
import com.kody.dawa.domain.questionnaire.enums.Division;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.user.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long>  {
    @Query("SELECT q FROM Questionnaire q WHERE q.yearMonthDay = :yearMonthDay ORDER BY q.serialNumber ASC")
    List<Questionnaire> findByYearMonthDayOrderBySerialNumber(@Param("yearMonthDay") String yearMonthDay);

    List<Questionnaire> findByUserId(Long userId);
}
