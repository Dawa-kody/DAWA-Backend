package com.kody.dawa.domain.auth.entity;

import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PasswordChangeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private String code;

    private LocalDateTime authCodeExpiresAt;
    public PasswordChangeCode(EmailCodeRequest emailCodeRequest) {
        this.email = emailCodeRequest.getEmail();
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
