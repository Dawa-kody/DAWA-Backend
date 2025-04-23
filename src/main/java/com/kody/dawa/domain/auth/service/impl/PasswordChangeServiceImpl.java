package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import com.kody.dawa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
}
