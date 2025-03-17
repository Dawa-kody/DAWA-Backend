package com.kody.dawa.domain.auth.entity;

import com.kody.dawa.domain.auth.presentation.dto.request.AuthCodeRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Table(name = "auth_code")
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "code", nullable = false, length = 5)
    private String code;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    public AuthCode(AuthCodeRequest request) {
        this.email = request.getEmail();
        this.code = generateAuthCode();
        this.expiresAt = LocalDateTime.now().plusMinutes(3);
    }

    private String generateAuthCode() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiresAt);
    }
}
