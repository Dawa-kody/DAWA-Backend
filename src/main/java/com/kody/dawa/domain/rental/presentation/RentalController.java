package com.kody.dawa.domain.rental.presentation;

import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rental")
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/write")
    public void createRental(@RequestBody RentalRequest request) {
        rentalService.createRental(request);
    }

    @PatchMapping("/{id}")
    public void rentalCompleted(@PathVariable Long id) {
        rentalService.rentalCompleted(id);
    }


}
