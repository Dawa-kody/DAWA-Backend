package com.kody.dawa.domain.questionnaire.repository;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
