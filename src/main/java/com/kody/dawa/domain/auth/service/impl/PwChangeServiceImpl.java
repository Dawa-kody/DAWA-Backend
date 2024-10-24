package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.entity.AuthCode;
import com.kody.dawa.domain.auth.presentation.dto.request.PwChangeRequest;
import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import com.kody.dawa.domain.auth.service.PwChangeService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.service.GetUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PwChangeServiceImpl implements PwChangeService {
    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final GetUser getUser;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void execute(PwChangeRequest request) {
        AuthCode findCode = authCodeRepository.findByEmail(request.getEmail());
        UUID requestCode = UUID.fromString(request.getCode());
        if (findCode == null) {
            throw new RuntimeException("인증 코드가 존재하지 않습니다.");
        }

        if (requestCode.equals(findCode.getCode())) {
            updatePassword(request.getPassword());
            deleteByEmail(request.getEmail());
        } else {
            throw new RuntimeException("잘못된 인증 코드입니다.");
        }
    }

    private void deleteByEmail(String email) {
        authCodeRepository.deleteByEmail(email);
    }

    private void updatePassword(String newPassword) {
        User user = getUser.getCurrentUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
