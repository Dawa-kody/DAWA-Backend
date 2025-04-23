package com.kody.dawa.global.auth;
import com.kody.dawa.domain.user.enums.Role;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        Long id = Long.parseLong(username);

        UserCredential credential = userRepository.findCredentialById(id)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다" + id));
        List<Role> roles = userRepository.findById(id)
                .map(user -> user.getRoles())
                .orElseThrow(() -> new UsernameNotFoundException("이 유저의 권한을 찾을 수 없습니다 : " + id));

        return new AuthDetails(credential, roles);
    }
}
