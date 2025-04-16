package com.kody.dawa.domain.questionnaire.repository;

import com.kody.dawa.domain.questionnaire.entity.Statistics;
import com.kody.dawa.domain.questionnaire.enums.Division;
import com.kody.dawa.domain.user.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query("SELECT qs FROM Statistics qs " +
            "WHERE qs.dateType = :dateType " +
            "AND qs.date = :date " +
            "AND qs.gender = :gender " +
            "AND qs.division = :division")
    Optional<Statistics> findByDateTypeAndDateAndGenderAndDivision(
            @Param("dateType") String dateType,
            @Param("date") String date,
            @Param("gender") Gender gender,
            @Param("division") Division division);
}
