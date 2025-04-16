package com.kody.dawa.domain.questionnaire.presentation.dto.response;

import com.kody.dawa.domain.questionnaire.enums.Division;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionnaireResponse {
    private Long questionnaireId;
    private Long serialNumber;
    private String schoolNumber;
    private String userName;
    private String gender;
    private Division division;
    private String disease;
    private String treatment;
    private Long quantity;
    private String medication1;
    private Long quantity1;
    private String medication2;
    private Long quantity2;
    private String notes;
}
