package com.kody.dawa.domain.user.repository;

import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUserId(Long userId);

    List<Role> findByUser(User user);
}
