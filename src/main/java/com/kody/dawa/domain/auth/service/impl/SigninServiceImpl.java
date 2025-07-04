package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.presentation.dto.request.SigninRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.SignInResponse;
import com.kody.dawa.domain.auth.service.SigninService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.JwtType;
import com.kody.dawa.global.exception.HttpException;
import com.kody.dawa.global.security.jwt.JwtProvider;
import com.kody.dawa.global.security.jwt.dto.JwtDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SigninServiceImpl implements SigninService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignInResponse execute(SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "없는 유저 입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new HttpException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");

        JwtDetails accessToken = jwtProvider.generateToken(user.getId(), user.getRoles().get(0),JwtType.ACCESS_TOKEN);
        JwtDetails refreshToken = jwtProvider.generateToken(user.getId(), user.getRoles().get(0),JwtType.REFRESH_TOKEN);

        return SignInResponse.builder()
                .accessToken(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .role(user.getRoles().get(0))
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}

