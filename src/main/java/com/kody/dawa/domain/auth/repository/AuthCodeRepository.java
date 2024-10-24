package com.kody.dawa.domain.auth.repository;

import com.kody.dawa.domain.auth.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {
    public AuthCode findByEmail(String email);
    public AuthCode findByCode(UUID code);
    public boolean existsByEmail(String email);
    public void deleteByEmail(String email);
}
