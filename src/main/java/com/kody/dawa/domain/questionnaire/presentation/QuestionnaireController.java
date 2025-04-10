package com.kody.dawa.domain.questionnaire.presentation;

import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentRecordResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.StudentSearchResponse;
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
    public void createQuestionnaires(@RequestBody QuestionnaireRequest request) {
        questionnaireService.createQuestionnaire(request);
    }

    @GetMapping("/date")
    public ResponseEntity<List<QuestionnaireResponse>> getQuestionnairesByYearMonthDay(@RequestParam(required = false) String yearMonthDay) {
        List<QuestionnaireResponse> response = questionnaireService.getQuestionnairesByYearMonthDay(yearMonthDay);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/studentRecord/{id}")
    public ResponseEntity<List<StudentRecordResponse>> recordStudent(@PathVariable Long id) {
        List<StudentRecordResponse> response = questionnaireService.recordStudent(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<List<StudentSearchResponse>> searchStudent(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String schoolNumber) {
        List<StudentSearchResponse> response = questionnaireService.searchStudent(userName, schoolNumber);
        return ResponseEntity.ok(response);
    }
}
