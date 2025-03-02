package com.kody.dawa.domain.questionnaire.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionnaireResponse {
    private String serialNumber;
    private String userName;
    private String schoolNumber;
    private String disease;
    private String content;
    private String time;
    private String gender;
}
