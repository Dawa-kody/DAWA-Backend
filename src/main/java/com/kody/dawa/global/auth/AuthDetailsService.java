package com.kody.dawa.global.auth;
import com.kody.dawa.domain.user.repository.RoleRepository;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Long id = Long.parseLong(username);

        UserCredential credential = userRepository.findCredentialById(id)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다" + id));

        var roles = roleRepository.findByUserId(credential.getId());

        return new AuthDetails(credential, roles);
    }
}
