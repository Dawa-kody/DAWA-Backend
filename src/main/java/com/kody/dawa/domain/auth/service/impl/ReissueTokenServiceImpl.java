package com.kody.dawa.domain.auth.service.impl;


import com.kody.dawa.domain.auth.presentation.dto.response.ReissueTokenResponse;
import com.kody.dawa.domain.auth.service.ReissueTokenService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.JwtType;
import com.kody.dawa.global.exception.HttpException;
import com.kody.dawa.global.exception.enums.ExceptionEnum;
import com.kody.dawa.global.security.jwt.JwtProvider;
import com.kody.dawa.global.security.jwt.dto.JwtDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    public ReissueTokenResponse execute(String resolveRefreshToken) {
        Long currentUserId = Long.parseLong(jwtProvider.getIdByRefreshToken(resolveRefreshToken));
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "없는 유저 입니다."));

        if (!jwtProvider.validateToken(resolveRefreshToken, JwtType.REFRESH_TOKEN)) {
            throw ExceptionEnum.AUTH_EXPIRED_TOKEN.toHttpException();
        }
        JwtDetails newAccessToken = jwtProvider.generateToken(currentUserId, user.getRoles().get(0),JwtType.ACCESS_TOKEN);
        JwtDetails newRefreshToken = jwtProvider.generateToken(currentUserId, user.getRoles().get(0),JwtType.REFRESH_TOKEN);

        return new ReissueTokenResponse(
                newAccessToken.getToken(),
                newAccessToken.getExpiredAt(),
                newRefreshToken.getToken(),
                newRefreshToken.getExpiredAt()
        );
    }

}