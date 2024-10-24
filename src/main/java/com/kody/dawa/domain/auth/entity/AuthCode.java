package com.kody.dawa.domain.auth.entity;

import com.kody.dawa.domain.auth.presentation.dto.request.AuthCodeRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "auth_code")
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthCode {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID code;

    @Column(name = "email", unique = true)
    String email;

    public AuthCode(AuthCodeRequest request) {
        this.email = request.getEmail();
    }
}
