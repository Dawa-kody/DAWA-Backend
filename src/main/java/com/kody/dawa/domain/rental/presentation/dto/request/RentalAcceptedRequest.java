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
    @NotBlank
    private boolean accepted;

    private String content;
    @NotBlank
    private String schoolNumber;
}
