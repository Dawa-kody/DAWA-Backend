package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.presentation.dto.response.TokenResponse;
import com.kody.dawa.domain.auth.service.RefreshService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.TokenType;
import com.kody.dawa.global.exception.HttpException;
import com.kody.dawa.global.security.jwt.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    @Transactional
    public TokenResponse execute(String access, String refresh){
        access = access.substring(7);
        refresh = refresh.substring(7);

        Boolean validateAccess = tokenProvider.validateToken(access);
        Boolean validateRefresh = tokenProvider.validateToken(refresh);

        User user = userRepository.findById(
                Long.parseLong(tokenProvider.getClaims(access).getSubject())
        ).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "해당하는 유저를 찾을 수 없습니다."));

        if(validateAccess && validateRefresh) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "엑세스 토큰과 리프레스 토큰이 모두 만료되었습니다.");
        } else if(validateAccess) {
            return new TokenResponse(
                    tokenProvider.generateToken(user.getId(), TokenType.ACCESS),
                    refresh
            );
        } else if(validateRefresh) {
            return new TokenResponse(
                    access,
                    tokenProvider.generateToken(user.getId(), TokenType.REFRESH)
            );
        } else {
            throw new HttpException(HttpStatus.BAD_REQUEST, "만료된 토큰이 없습니다");
        }
    }
}
