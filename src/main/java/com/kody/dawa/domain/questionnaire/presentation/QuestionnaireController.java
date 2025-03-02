package com.kody.dawa.domain.questionnaire.presentation;

import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.service.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/questionnaire")
@RestController
@RequiredArgsConstructor
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @PostMapping("/write")
    public void createQuestionnaires(@RequestBody List<QuestionnaireRequest> requests) {
        questionnaireService.createQuestionnaires(requests);
    }

    @GetMapping("/date")
    public ResponseEntity<List<QuestionnaireResponse>> getQuestionnairesByYearMonthDay(@RequestParam String yearMonthDay) {
        List<QuestionnaireResponse> response = questionnaireService.getQuestionnairesByYearMonthDay(yearMonthDay);
        return ResponseEntity.ok(response);
    }
}
