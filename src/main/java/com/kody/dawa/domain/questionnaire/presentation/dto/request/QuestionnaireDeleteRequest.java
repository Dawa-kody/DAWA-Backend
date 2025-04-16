package com.kody.dawa.domain.questionnaire.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireDeleteRequest {
    private Long questionnaireId;
    private String treatment;
    private Long quantity;
    private String medication1;
    private Long quantity1;
    private String medication2;
    private Long quantity2;
}
