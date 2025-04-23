package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.entity.AuthCode;
import com.kody.dawa.domain.auth.entity.enums.VerifyCodeType;
import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.EmailVerifyCodeRequest;
import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import com.kody.dawa.domain.auth.service.EmailVerifyService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.exception.HttpException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerifyServiceImpl implements EmailVerifyService {
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final AuthCodeRepository authCodeRepository;

    @Transactional
    public void sendSignupMail(EmailCodeRequest request) {
        if(userRepository.existsUserByEmail(request.getEmail()))
            throw new HttpException(HttpStatus.BAD_REQUEST, "이미 해당 메일을 사용하는 유저가 존재합니다.");
        authCodeRepository.deleteByEmail(request.getEmail());
        AuthCode emailVerifyCode = authCodeRepository.save(new AuthCode(request, VerifyCodeType.SIGNUP));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailVerifyCode.getEmail());
        mailMessage.setSubject("Dawa 이메일 확인 코드 입니다.");
        mailMessage.setText("이메일 인증 코드 입니다.\n" + emailVerifyCode.getCode());
        javaMailSender.send(mailMessage);
    }

    @Transactional
    public void emailVerify(EmailVerifyCodeRequest request) {
        AuthCode code = authCodeRepository.findByEmail(request.getEmail());
        if (code == null) {
            throw new RuntimeException("인증 코드가 존재하지 않습니다.");
        }
        if (code.getType() == VerifyCodeType.PASSWORD_RESET) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "회원가입 인증 코드가 아닙니다.");
        }
        if (code.isAuthCodeExpired()) {
            authCodeRepository.deleteByEmail(request.getEmail());
            throw new RuntimeException("인증 코드가 만료되었습니다.");
        }
        if (!code.getCode().equals(request.getCode())) {
            throw new RuntimeException("잘못된 인증 코드입니다.");
        }
        User user = User.builder()
                .email(request.getEmail())
                .emailVerifyStatus(true)
                .build();
        userRepository.save(user);
        authCodeRepository.deleteByEmail(request.getEmail());
    }
}
