package com.kody.dawa.global.security.jwt.dto;


import java.time.Instant;

public class JwtDetails {
    private final String token;
    private final Instant expiredAt;

    public JwtDetails(String token, Instant expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiredAt() {
        return expiredAt;
    }
}