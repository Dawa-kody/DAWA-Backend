package com.kody.dawa.domain.auth.repository;

import com.kody.dawa.domain.auth.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {
    AuthCode findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
