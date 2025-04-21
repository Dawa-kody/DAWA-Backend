package com.kody.dawa.domain.excel.repository;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRepository extends JpaRepository<Questionnaire,Long> {
//    List<Questionnaire> findBySchoolNumber(String schoolNumber);

    List<Questionnaire> findByYearMonthDay(String yearMonthDay);

    @Query("SELECT q FROM Questionnaire q WHERE q.yearMonthDay LIKE CONCAT(:yearMonth,'.%')")
    List<Questionnaire> findByYearAndMonth(@Param("yearMonth") String yearMonth);

//    @Query("SELECT q FROM Questionnaire q WHERE q.formattedDate LIKE :formattedDate")
//    List<Questionnaire> findByFormattedDate(@Param("formattedDate") String formattedDate);
}
