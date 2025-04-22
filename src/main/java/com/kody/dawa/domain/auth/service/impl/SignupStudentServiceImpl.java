package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.service.SignupStudentService;
import com.kody.dawa.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupStudentServiceImpl implements SignupStudentService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;


}
