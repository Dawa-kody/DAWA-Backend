package com.kody.dawa.domain.rental.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyRentalResponse {
    private String formattedDate;
    private String count;
    private String rental;
    private boolean isRentaled;
}
