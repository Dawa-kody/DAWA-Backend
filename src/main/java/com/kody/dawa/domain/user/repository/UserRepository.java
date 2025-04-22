package com.kody.dawa.domain.user.repository;

import com.kody.dawa.global.entity.UserCredential;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;
import com.kody.dawa.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsUserByEmail(String email);

    Optional<User> findBySchoolNumber(String schoolNumber);

    List<User> findByNameOrSchoolNumber(String name, String schoolNumber);

    Optional<UserCredential> findCredentialById(Long userId);
}