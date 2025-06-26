package com.kody.dawa.global.security.cookie;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
@RequiredArgsConstructor
public class AddTokenCookie {
    public static void addTokenCookies(HttpServletResponse response,
                                       String accessToken, Instant accessExpireAt,
                                       String refreshToken, Instant refreshExpireAt) {

        Cookie accessCookie = new Cookie("accessToken", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setPath("/");
        accessCookie.setMaxAge((int) Duration.between(Instant.now(), accessExpireAt).getSeconds());
        response.addCookie(accessCookie);

        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge((int) Duration.between(Instant.now(), refreshExpireAt).getSeconds());
        response.addCookie(refreshCookie);
    }
}
