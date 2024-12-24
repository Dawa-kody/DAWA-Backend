package com.kody.dawa.domain.rental.service;

import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.response.AllRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.MyRentalResponse;

import java.util.List;

public interface RentalService {
    void createRental(RentalRequest request);

    void rentalCompleted(Long id);
    List<MyRentalResponse> getMyRentals();
    List<AllRentalResponse> getAllRentals();
}
