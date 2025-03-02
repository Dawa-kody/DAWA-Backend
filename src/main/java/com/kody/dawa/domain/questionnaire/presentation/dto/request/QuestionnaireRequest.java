package com.kody.dawa.domain.questionnaire.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireRequest {
    private String serialNumber;
    private String userName;
    private String schoolNumber;
    private String disease;
    private String content;
    private String time;
    private String gender;
}
