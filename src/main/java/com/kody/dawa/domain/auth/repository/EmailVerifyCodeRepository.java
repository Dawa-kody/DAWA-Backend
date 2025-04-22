package com.kody.dawa.domain.auth.repository;

import com.kody.dawa.domain.auth.entity.EmailVerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailVerifyCodeRepository extends JpaRepository<EmailVerifyCode, UUID> {
    EmailVerifyCodeRepository findByEmail(String email);
    void deleteByEmail(String email);
}
