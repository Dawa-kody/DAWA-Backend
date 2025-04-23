package com.kody.dawa.domain.auth.repository;

import com.kody.dawa.domain.auth.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthCodeRepository extends JpaRepository<AuthCode, UUID> {
    AuthCode findByEmail(String email);
    void deleteByEmail(String email);
}
