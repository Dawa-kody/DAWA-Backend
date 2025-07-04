package com.kody.dawa.domain.rental.service.impl;

import com.kody.dawa.domain.mail.entity.Mail;
import com.kody.dawa.domain.mail.repository.MailRepository;
import com.kody.dawa.domain.mail.service.MailService;
import com.kody.dawa.domain.rental.entity.Rental;
import com.kody.dawa.domain.rental.presentation.dto.request.RentalAcceptedRequest;
import com.kody.dawa.domain.rental.presentation.dto.request.RentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.request.StudentRentalRequest;
import com.kody.dawa.domain.rental.presentation.dto.response.AllRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.MyRentalResponse;
import com.kody.dawa.domain.rental.presentation.dto.response.RentalAcceptResponse;
import com.kody.dawa.domain.rental.repository.RentalRepository;
import com.kody.dawa.domain.rental.service.RentalService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.domain.user.service.GetUser;
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
    private final MailRepository mailRepository;
    private final MailService mailService;
    public void createRental(RentalRequest request) {
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("잘못된 학번 입니다."));
        Rental rental = Rental.builder()
                .user(user)
                .count(request.getCount())
                .isAccepted(true)
                .isRentaled(false)
                .rental(request.getRental())
                .build();
        rentalRepository.save(rental);
    }

    public void createStudentRental(StudentRentalRequest request) {
        User user = getUser.getCurrentUser();
        Rental rental = Rental.builder()
                .user(user)
                .count(request.getCount())
                .isAccepted(false)
                .isRentaled(false)
                .rental(request.getRental())
                .build();
        rentalRepository.save(rental);
    }

    public void rentalCompleted(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 요청입니다."));
        rental.setIsRentaled(true);
        rentalRepository.save(rental);
    }

    public void rentalAccepted(Long id, RentalAcceptedRequest request) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 요청입니다."));
        User user = userRepository.findBySchoolNumber(request.getSchoolNumber())
                .orElseThrow(() -> new RuntimeException("없는 유저입니다."));
        Mail mail = Mail.builder()
                .content(request.getContent())
                .count(rental.getCount())
                .item(rental.getRental())
                .user(user)
                .build();
        mailRepository.save(mail);
        mailService.send(user.getSchoolNumber(), mail);
        if (request.isAccepted()) {
            rental.setIsAccepted(true);
            rentalRepository.save(rental);
        } else {
            rentalRepository.delete(rental);
        }

    }

    public void rentalCancel(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 요청입니다."));
        rentalRepository.delete(rental);
    }

    public List<RentalAcceptResponse> getRentalAccept() {
        List<Rental> rentals = rentalRepository.findAllRentalsWhereAcceptedIsFalseOrderByCreateAtDesc();
        return rentals.stream()
                .map(rental -> RentalAcceptResponse.builder()
                        .count(rental.getCount())
                        .rentalId(rental.getId())
                        .rental(rental.getRental())
                        .name(rental.getUser().getName())
                        .schoolNumber(rental.getUser().getSchoolNumber())
                        .build())
                .collect(Collectors.toList());
    }
    public List<MyRentalResponse> getMyRentals() {
        User user = getUser.getCurrentUser();
        List<Rental> rentals = rentalRepository.findRentalsByUserOrderByCreateAtDesc(user);
        return rentals.stream()
                .map(rental -> MyRentalResponse.builder()
                        .count(rental.getCount())
                        .rentalId(rental.getId())
                        .rental(rental.getRental())
                        .formattedDate(rental.getFormattedDate())
                        .isRentaled(rental.isRentaled())
                        .isAccepted(rental.isAccepted())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AllRentalResponse> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAllAcceptedRentalsOrderByCreateAtDesc();
        return rentals.stream()
                .map(rental -> AllRentalResponse.builder()
                        .count(rental.getCount())
                        .rental(rental.getRental())
                        .name(rental.getUser().getName())
                        .rentalId(rental.getId())
                        .isRentaled(rental.isRentaled())
                        .formattedDate(rental.getFormattedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
