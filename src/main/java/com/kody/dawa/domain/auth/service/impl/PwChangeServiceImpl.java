package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.entity.AuthCode;
import com.kody.dawa.domain.auth.presentation.dto.request.PwChangeRequest;
import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import com.kody.dawa.domain.auth.service.PwChangeService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PwChangeServiceImpl implements PwChangeService {
    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void passwordChange(PwChangeRequest request) {
        AuthCode findCode = authCodeRepository.findByEmail(request.getEmail());

        if (findCode == null) {
            throw new RuntimeException("인증 코드가 존재하지 않습니다.");
        }
        if (findCode.isExpired()) {
            authCodeRepository.deleteByEmail(request.getEmail());
            throw new RuntimeException("인증 코드가 만료되었습니다.");
        }
        if (!findCode.getCode().equals(request.getCode())) {
            throw new RuntimeException("잘못된 인증 코드입니다.");
        }
        if (request.getPassword() == null) {
            throw new RuntimeException("새 비밀번호가 존재하지 않습니다.");
        }

        updatePassword(request.getPassword(), request.getEmail());
        authCodeRepository.deleteByEmail(request.getEmail());
    }

    private void updatePassword(String newPassword, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자가 존재하지 않습니다."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
