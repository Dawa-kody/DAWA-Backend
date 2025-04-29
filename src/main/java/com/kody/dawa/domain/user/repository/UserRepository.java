package com.kody.dawa.domain.user.repository;

import com.kody.dawa.global.entity.UserCredential;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import com.kody.dawa.domain.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsUserByEmail(String email);

    Optional<User> findBySchoolNumber(String schoolNumber);

    List<User> findByNameOrSchoolNumber(String name, String schoolNumber);
    @Query("SELECT new com.kody.dawa.global.entity.UserCredential(u.id, u.email, u.password) FROM User u WHERE u.id = :userId")
    Optional<UserCredential> findCredentialById(Long userId);

    @Query("""
    SELECT u FROM User u
    WHERE 
        (:classes IS NULL OR SUBSTRING(u.schoolNumber, 1, 2) IN :classes)
        AND (
            :keyword IS NULL OR u.name LIKE %:keyword% OR u.schoolNumber LIKE %:keyword%
        )
    ORDER BY u.schoolNumber ASC
""")
    List<User> findAllUserBySchoolNumberOrName(
            @Param("classes") List<String> classes,
            @Param("keyword") String keyword
    );

}