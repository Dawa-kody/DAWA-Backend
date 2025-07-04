package com.kody.dawa.global.filter;

import com.kody.dawa.global.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null) {
            try {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String tokenRole = jwtProvider.getRoleFromToken(token);

                String cookieRole = getRoleFromCookies(request);

                if (cookieRole == null || !cookieRole.equals(tokenRole)) {
                    log.warn("역할 불일치 - 쿠키 ROLE: {}, 어세스 토큰 ROLE: {}", cookieRole, tokenRole);
                    throw new IllegalStateException("클라이언트의 role 쿠키 값이 JWT의 role 정보와 일치하지 않습니다." + tokenRole);
                }

            } catch (Exception e) {
                log.warn("JWT 인증 실패: {}", e.getMessage());
                throw e;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    private String getRoleFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("role".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}


