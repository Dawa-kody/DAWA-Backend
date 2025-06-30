package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.presentation.dto.request.SigninRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.SignInResponse;
import com.kody.dawa.domain.auth.service.SigninService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.JwtType;
import com.kody.dawa.global.exception.HttpException;
import com.kody.dawa.global.security.cookie.CookieProperties;
import com.kody.dawa.global.security.jwt.JwtProvider;
import com.kody.dawa.global.security.jwt.dto.JwtDetails;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SigninServiceImpl implements SigninService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CookieProperties cookieProperties;

    @Transactional
    public SignInResponse execute(SigninRequest request, HttpServletResponse response) {
        System.out.println("[execute] 로그인 시도: email=" + request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "없는 유저 입니다."));
        System.out.println("[execute] 유저 조회 성공: id=" + user.getId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.out.println("[execute] 비밀번호 불일치");
            throw new HttpException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }
        System.out.println("[execute] 비밀번호 일치");

        JwtDetails accessToken = jwtProvider.generateToken(user.getId(), JwtType.ACCESS_TOKEN);
        JwtDetails refreshToken = jwtProvider.generateToken(user.getId(), JwtType.REFRESH_TOKEN);

        System.out.println("[execute] 토큰 생성 완료");
        System.out.println("  AccessToken expires at: " + accessToken.getExpiredAt());
        System.out.println("  RefreshToken expires at: " + refreshToken.getExpiredAt());

        // ✅ 정상 작동하는 방식처럼 분리된 메서드 사용
        issueJwtCookies(response, accessToken, refreshToken);

        return SignInResponse.builder()
                .accessToken(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .role(user.getRoles().get(0))
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }

    private void issueJwtCookies(HttpServletResponse response, JwtDetails accessToken, JwtDetails refreshToken) {
        long accessMaxAge = accessToken.getExpiredAt().toEpochMilli() - Instant.now().toEpochMilli();
        long refreshMaxAge = refreshToken.getExpiredAt().toEpochMilli() - Instant.now().toEpochMilli();

        System.out.println("[issueJwtCookies] accessMaxAge(ms) = " + accessMaxAge);
        System.out.println("[issueJwtCookies] refreshMaxAge(ms) = " + refreshMaxAge);

        setJwtCookie(response, "access_token", accessToken.getToken(), accessMaxAge);
        setJwtCookie(response, "refresh_token", refreshToken.getToken(), refreshMaxAge);
    }

    private void setJwtCookie(HttpServletResponse response, String name, String token, long maxAgeMs) {
        long maxAgeSec = Math.max(10, maxAgeMs / 1000);

        String domain = cookieProperties.getDomain();
        boolean isLocalhost = domain.equalsIgnoreCase("localhost") || domain == null || domain.isBlank();

        boolean secure = !isLocalhost;
        String sameSite = isLocalhost ? "Lax" : "None";

        System.out.println("[setJwtCookie] 쿠키 발급 시도됨 → 이름: " + name + ", 길이: " + token.length());
        System.out.println("[setJwtCookie] maxAge(ms): " + maxAgeMs);
        System.out.println("[setJwtCookie] maxAge(sec, min 10): " + maxAgeSec);
        System.out.println("[setJwtCookie] domain: " + domain);
        System.out.println("[setJwtCookie] secure: " + secure);
        System.out.println("[setJwtCookie] sameSite: " + sameSite);

        ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from(name, token)
                .httpOnly(true)
                .secure(secure)
                .sameSite(sameSite)
                .path("/")
                .maxAge(Duration.ofSeconds(maxAgeSec));

        if (!isLocalhost) {
            builder.domain(domain);
            System.out.println("[setJwtCookie] 도메인 설정 추가됨: " + domain);
        } else {
            System.out.println("[setJwtCookie] localhost이므로 domain 미설정");
        }

        ResponseCookie cookie = builder.build();
        response.addHeader("Set-Cookie", cookie.toString());

        System.out.println("[setJwtCookie] 최종 Set-Cookie 헤더: " + cookie);
    }
}

