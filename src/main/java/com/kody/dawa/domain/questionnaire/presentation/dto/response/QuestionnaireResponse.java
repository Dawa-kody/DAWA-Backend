package com.kody.dawa.domain.questionnaire.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionnaireResponse {
    private String serialNumber;
    private String schoolNumber;
    private String userName;
    private String gender;
    private String division;
    private String disease;
    private String treatment;
    private Long quantity;
    private String medication1;
    private Long quantity1;
    private String medication2;
    private Long quantity2;
    private String notes;
}
