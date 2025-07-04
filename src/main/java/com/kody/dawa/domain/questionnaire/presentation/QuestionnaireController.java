package com.kody.dawa.domain.questionnaire.presentation;

import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireDeleteRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireResponse;
import com.kody.dawa.domain.questionnaire.presentation.dto.response.QuestionnaireStatisticsResponse;
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

    @PostMapping("/write") //admin
    public void createQuestionnaire(@RequestBody QuestionnaireRequest request) {
        questionnaireService.createQuestionnaire(request);
    }

    @DeleteMapping("/delete") //admin
    public void deleteQuestionnaire(@RequestBody QuestionnaireDeleteRequest request) {
        questionnaireService.deleteQuestionnaire(request);
    }

    @GetMapping("/date") //admin
    public ResponseEntity<QuestionnaireStatisticsResponse> getQuestionnairesByYearMonthDay(@RequestParam(required = false) String yearMonthDay) {
        QuestionnaireStatisticsResponse response = questionnaireService.getQuestionnairesByYearMonthDay(yearMonthDay);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/studentRecord/{id}") //admin
    public ResponseEntity<List<StudentRecordResponse>> recordStudent(@PathVariable Long id) {
        List<StudentRecordResponse> response = questionnaireService.recordStudent(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search") //admin
    public ResponseEntity<List<StudentSearchResponse>> searchStudent(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String schoolNumber) {
        List<StudentSearchResponse> response = questionnaireService.searchStudent(userName, schoolNumber);
        return ResponseEntity.ok(response);
    }
}
