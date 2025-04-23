package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.entity.AuthCode;
import com.kody.dawa.domain.auth.entity.enums.VerifyCodeType;
import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.PasswordChangeRequest;
import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.global.exception.HttpException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PasswordChangeServiceImpl {
    private final AuthCodeRepository authCodeRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void sendMail(EmailCodeRequest request) {
        authCodeRepository.deleteByEmail(request.getEmail());
        AuthCode passwordChangeCode = authCodeRepository.save(new AuthCode(request, VerifyCodeType.PASSWORD_RESET));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(passwordChangeCode.getEmail());
        mailMessage.setSubject("Dawa 비밀번호 변경 확인 코드 입니다.");
        mailMessage.setText("비밀번호 변경 인증 코드 입니다.\n" + passwordChangeCode.getCode());
        javaMailSender.send(mailMessage);
    }

    @Transactional
    public void passwordChange(PasswordChangeRequest request) {
        AuthCode findCode = authCodeRepository.findByEmail(request.getEmail());


        if (findCode == null) {
            throw new RuntimeException("인증 코드가 존재하지 않습니다.");
        }
        if (findCode.getType() == VerifyCodeType.SIGNUP) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "비밀번호 인증 코드가 아닙니다.");
        }
        if (findCode.isAuthCodeExpired()) {
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
