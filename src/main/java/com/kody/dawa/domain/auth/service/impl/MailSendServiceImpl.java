package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.entity.AuthCode;
import com.kody.dawa.domain.auth.presentation.dto.request.AuthCodeRequest;
import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import com.kody.dawa.domain.auth.service.MailSendService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {
    private final AuthCodeRepository authCodeRepository;
    private final JavaMailSender javaMailSender;
    @Transactional
    public void sendMail(AuthCodeRequest request) {
        authCodeRepository.deleteByEmail(request.getEmail());
        AuthCode authCode = authCodeRepository.save(new AuthCode(request));

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(authCode.getEmail());
        mailMessage.setSubject("dawa 비밀번호 변경 확인 코드 입니다.");
        mailMessage.setText("비밀번호 변경 인증 코드 입니다.\n" + authCode.getCode());
        javaMailSender.send(mailMessage);
    }
}
