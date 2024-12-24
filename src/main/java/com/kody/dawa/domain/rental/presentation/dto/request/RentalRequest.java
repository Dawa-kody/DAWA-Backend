package com.kody.dawa.domain.rental.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {
    private String schoolNumber;
    private String rental;
    private String count;
}
