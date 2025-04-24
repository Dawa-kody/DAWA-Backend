package com.kody.dawa.domain.user.service;

import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.exception.HttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUser {
    private final UserRepository userRepository;

    @Transactional
    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."));
    }
}
