package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.presentation.dto.request.SignupRequest;
import com.kody.dawa.domain.auth.service.SignupService;
import com.kody.dawa.domain.user.entity.HealthIssueDetail;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.exception.HttpException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void signup(SignupRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "없는 유저 입니다."));

        if(!user.isEmailVerifyStatus()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "인증되지 않은 유저입니다.");
        }

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            throw new HttpException(HttpStatus.CONFLICT, "이미 회원가입된 유저입니다.");
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HealthIssueDetail healthIssueDetail = new HealthIssueDetail();
        if (request.getHealthIssues() != null) {
            healthIssueDetail.setAllergyImmune(request.getHealthIssues().getAllergyImmune());
            healthIssueDetail.setChronicMedication(request.getHealthIssues().getChronicMedication());
            healthIssueDetail.setEmergencyPossible(request.getHealthIssues().getEmergencyPossible());
            healthIssueDetail.setEtc(request.getHealthIssues().getEtc());
        }
        user.setHealthIssueDetail(healthIssueDetail);
        userRepository.save(user);
    }
}
