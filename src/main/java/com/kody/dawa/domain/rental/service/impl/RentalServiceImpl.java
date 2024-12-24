package com.kody.dawa.domain.rental.service.impl;

import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.questionnaire.presentation.dto.request.QuestionnaireRequest;
import com.kody.dawa.domain.questionnaire.repository.QuestionnaireRepository;
import com.kody.dawa.domain.rental.entity.Rental;
import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.repository.RentalRepository;
import com.kody.dawa.domain.rental.service.RentalService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    public void createRental(RentalRequest request) {
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("잘못된 학번 입니다."));
        Rental rental = Rental.builder()
                .user(user)
                .count(request.getCount())
                .isReturn(false)
                .rental(request.getRental())
                .build();
        rentalRepository.save(rental);
    }

    public void rentalCompleted(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없음"));
        rental.setReturn(true);
    }
}
