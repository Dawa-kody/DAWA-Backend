package com.kody.dawa.domain.user.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;
import com.kody.dawa.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsUserByEmail(String email);

    Optional<User> findBySchoolNumber(String studentNumber);
    List<User> findByNameOrSchoolNumber(String userName, String schoolNumber);

}