package com.kody.dawa.domain.rental.service;

import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;

public interface RentalService {
    void createRental(RentalRequest request);

    void rentalCompleted(Long id);
}
