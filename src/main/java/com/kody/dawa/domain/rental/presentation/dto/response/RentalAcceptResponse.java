package com.kody.dawa.domain.rental.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RentalAcceptResponse {
    private String count;
    private String rental;
    private Long rentalId;
    private String name;
    private String schoolNumber;
}
