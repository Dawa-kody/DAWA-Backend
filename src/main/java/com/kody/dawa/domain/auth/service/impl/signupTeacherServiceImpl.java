package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.presentation.dto.request.SignupTeacherRequest;
import com.kody.dawa.domain.auth.service.SignupTeacherService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.enums.Role;
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
public class signupTeacherServiceImpl implements SignupTeacherService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void signupTeacher(SignupTeacherRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "없는 유저 입니다."));

        if (user.getPassword() != null || user.getName() != null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "이미 회원가입을 완료한 유저입니다.");
        }

        if(!user.isEmailVerifyStatus()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "인증되지 않은 유저입니다");
        }

        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDepartment(request.getDepartment());
        user.setRoles(List.of(Role.ROLE_TEACHER));
        user.setGender(request.getGender());
        userRepository.save(user);
    }
}
