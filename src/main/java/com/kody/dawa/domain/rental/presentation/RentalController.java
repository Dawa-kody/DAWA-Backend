package com.kody.dawa.domain.rental.presentation;

import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.response.AllRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.MyRentalResponse;
import com.kody.dawa.domain.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/rental")
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/write")
    public void createRental(@RequestBody RentalRequest request) {
        rentalService.createRental(request);
    }

    @PutMapping("/{id}")
    public void rentalCompleted(@PathVariable Long id) {
        rentalService.rentalCompleted(id);
    }

    @GetMapping
    public List<MyRentalResponse> getMyRentals() {
        return rentalService.getMyRentals();
    }

    @GetMapping("/allRental")
    public List<AllRentalResponse> getAllRentals() {
        return rentalService.getAllRentals();
    }
}
