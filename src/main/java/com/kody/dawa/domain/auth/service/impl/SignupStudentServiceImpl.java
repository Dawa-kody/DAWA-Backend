package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.entity.EmailVerifyCode;
import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import com.kody.dawa.domain.auth.repository.EmailVerifyCodeRepository;
import com.kody.dawa.domain.auth.service.SignupStudentService;
import com.kody.dawa.domain.user.repository.UserRepository;
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
public class SignupStudentServiceImpl implements SignupStudentService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final EmailVerifyCodeRepository emailVerifyCodeRepository;

    @Transactional
    public void sendSignupMail(EmailCodeRequest request) {
        if(userRepository.existsUserByEmail(request.getEmail()))
            throw new HttpException(HttpStatus.BAD_REQUEST, "이미 해당 메일을 사용하는 유저가 존재합니다.");
        emailVerifyCodeRepository.deleteByEmail(request.getEmail());
        EmailVerifyCode emailVerifyCode = emailVerifyCodeRepository.save(new EmailVerifyCode(request));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailVerifyCode.getEmail());
        mailMessage.setSubject("Dawa 이메일 확인 코드 입니다.");
        mailMessage.setText("이메일 인증 코드 입니다.\n" + emailVerifyCode.getCode());
        javaMailSender.send(mailMessage);
    }


}
