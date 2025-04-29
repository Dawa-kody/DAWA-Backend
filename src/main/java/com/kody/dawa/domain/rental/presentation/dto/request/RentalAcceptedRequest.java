package com.kody.dawa.domain.rental.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalAcceptedRequest {
    private boolean accepted;
    @NotBlank
    private String content;
    @NotBlank
    private String schoolNumber;
}
