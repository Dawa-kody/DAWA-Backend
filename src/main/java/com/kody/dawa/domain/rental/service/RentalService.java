package com.kody.dawa.domain.rental.service;

import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.request.StudentRentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.response.AllRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.MyRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.RentalAcceptResponse;

import java.util.List;

public interface RentalService {
    void createRental(RentalRequest request);
    void rentalCompleted(Long id);
    void rentalAccepted(Long id);
    void rentalCancel(Long id);
    List<RentalAcceptResponse> getRentalAccept();
    List<MyRentalResponse> getMyRentals();
    List<AllRentalResponse> getAllRentals();
    void createStudentRental(StudentRentalRequest request);
}
