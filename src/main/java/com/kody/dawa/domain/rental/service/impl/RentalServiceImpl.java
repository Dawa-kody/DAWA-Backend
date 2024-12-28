package com.kody.dawa.domain.rental.service.impl;

import com.kody.dawa.domain.rental.entity.Rental;
import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.response.AllRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.MyRentalResponse;
import com.kody.dawa.domain.rental.repository.RentalRepository;
import com.kody.dawa.domain.rental.service.RentalService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.service.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final GetUser getUser;
    public void createRental(RentalRequest request) {
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("잘못된 학번 입니다."));
        Rental rental = Rental.builder()
                .user(user)
                .count(request.getCount())
                .isRentaled(false)
                .rental(request.getRental())
                .build();
        rentalRepository.save(rental);
    }

    public void rentalCompleted(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없음"));
        rental.setIsRentaled(true);
    }

    public List<MyRentalResponse> getMyRentals() {
        User user = getUser.getCurrentUser();
        List<Rental> rentals = rentalRepository.findRentalsByUserOrderByCreateAtDesc(user);
        return rentals.stream()
                .map(rental -> MyRentalResponse.builder()
                        .count(rental.getCount())
                        .rental(rental.getRental())
                        .formattedDate(rental.getFormattedDate())
                        .isRentaled(rental.isRentaled())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AllRentalResponse> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAllRentalsOrderByCreateAtDesc();
        return rentals.stream()
                .map(rental -> AllRentalResponse.builder()
                        .count(rental.getCount())
                        .rental(rental.getRental())
                        .name(rental.getUser().getName())
                        .isRentaled(rental.isRentaled())
                        .formattedDate(rental.getFormattedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
