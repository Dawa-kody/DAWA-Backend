package com.kody.dawa.domain.excel.repository;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRepository extends JpaRepository<Questionnaire,Long> {
    List<Questionnaire> findBySchoolNumber(String schoolNumber);
    List<Questionnaire> findByYearMonthDay(String yearMonthDay);
}
