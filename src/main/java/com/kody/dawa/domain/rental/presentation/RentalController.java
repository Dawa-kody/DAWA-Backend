package com.kody.dawa.domain.rental.presentation;

import com.kody.dawa.domain.rental.presentation.dto.request.RentalAcceptedRequest;
import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.request.StudentRentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.response.AllRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.MyRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.RentalAcceptResponse;
import com.kody.dawa.domain.rental.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/rental")
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/write") //admin
    public void createRental(@RequestBody RentalRequest request) {
        rentalService.createRental(request);
    }

    @PostMapping("/request")
    public void requestRental(@RequestBody StudentRentalRequest request) {
        rentalService.createStudentRental(request);
    }

    @PutMapping("/{id}") //admin
    public void rentalCompleted(@PathVariable Long id) {
        rentalService.rentalCompleted(id);
    }

    @GetMapping
    public List<MyRentalResponse> getMyRentals() {
        return rentalService.getMyRentals();
    }

    @GetMapping("/allRental") //admin
    public List<AllRentalResponse> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/rentalAccept") //admin
    public List<RentalAcceptResponse> getRentalAccept() {
        return rentalService.getRentalAccept();
    }

    @PutMapping("/rentalAccept/{id}") //admin
    public void rentalAccepted(@PathVariable Long id, @RequestBody @Valid RentalAcceptedRequest request) {
        rentalService.rentalAccepted(id, request);
    }

    @DeleteMapping("/rentalCancel/{id}") //admin
    public void rentalCancel(@PathVariable Long id) {
        rentalService.rentalCancel(id);
    }
}
