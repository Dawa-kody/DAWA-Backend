package com.kody.dawa.domain.auth.entity;

import com.kody.dawa.domain.auth.entity.enums.VerifyCodeType;
import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    @Enumerated(EnumType.STRING)
    private VerifyCodeType type;

    private String code;

    private LocalDateTime authCodeExpiresAt;

    public AuthCode(EmailCodeRequest emailCodeRequest, VerifyCodeType type) {
        this.email = emailCodeRequest.getEmail();
        this.type = type;
        this.code = generateCode();
        this.authCodeExpiresAt = LocalDateTime.now().plusMinutes(3);
    }

    private String generateCode() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    public boolean isAuthCodeExpired() {
        return LocalDateTime.now().isAfter(this.authCodeExpiresAt);
    }
}
