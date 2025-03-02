package com.kody.dawa.domain.questionnaire.presentation;

import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.service.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/questionnaire")
@RestController
@RequiredArgsConstructor
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @PostMapping("/write")
    public void createQuestionnaire(@RequestBody List<QuestionnaireRequest> requests) {
        questionnaireService.createQuestionnaires(requests);
    }


}
