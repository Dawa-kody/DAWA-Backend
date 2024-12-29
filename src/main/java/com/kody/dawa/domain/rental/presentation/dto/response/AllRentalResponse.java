package com.kody.dawa.domain.rental.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AllRentalResponse {
    private String formattedDate;
    private String count;
    private String rental;
    private String name;
    private boolean isRentaled;
}
