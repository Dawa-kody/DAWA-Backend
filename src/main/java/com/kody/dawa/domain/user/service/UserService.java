package com.kody.dawa.domain.user.service;

import com.kody.dawa.domain.user.entity.HealthIssueDetail;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.enums.Role;
import com.kody.dawa.domain.user.presentation.dto.request.UserRegisterRequest;
import com.kody.dawa.domain.user.presentation.dto.request.UserUpdateRequest;
import com.kody.dawa.domain.user.presentation.dto.response.UserHealthIssueDetailResponse;
import com.kody.dawa.domain.user.presentation.dto.response.UserResponse;
import com.kody.dawa.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private static final int BATCH_SIZE = 100;

    @Transactional
    public void registerUsers(List<UserRegisterRequest> requests) {

        for (int i = 0; i < requests.size(); i++) {
            UserRegisterRequest request = requests.get(i);

            User user = User.builder()
                    .email(request.getEmail())
                    .gender(request.getGender())
                    .name(request.getName())
                    .schoolNumber(request.getSchoolNumber())
                    .password(null)
                    .emailVerifyStatus(false)
                    .roles(List.of(Role.ROLE_USER))
                    .build();

            entityManager.persist(user);

            if ((i + 1) % BATCH_SIZE == 0) {
                entityManager.flush();
            }
        }

        entityManager.flush();
    }

    @Transactional
    public List<UserResponse> getAllUsers(List<String> classes, String name) {
        List<User> users = userRepository.findAllUserBySchoolNumberOrName(classes, name);

        return users.stream()
                .map(user -> new UserResponse(
                        user.getName(),
                        user.getGender(),
                        user.getSchoolNumber()))
                .collect(Collectors.toList());
    }


    public UserHealthIssueDetailResponse getHealthIssueDetail(String schoolNumber) {
        User user = userRepository.findBySchoolNumber(schoolNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.: " + schoolNumber));

        HealthIssueDetail detail = user.getHealthIssueDetail();

        return new UserHealthIssueDetailResponse(
                detail != null ? detail.getAllergyImmune() : null,
                detail != null ? detail.getChronicMedication() : null,
                detail != null ? detail.getEmergencyPossible() : null,
                detail != null ? detail.getEtc() : null
        );
    }

    @Transactional
    public void updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("이 유저를 찾을 수 없습니다"));

        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setName(request.getName());
        user.setSchoolNumber(request.getSchoolNumber());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("이 유저를 찾을 수 없습니다"));
        userRepository.delete(user);
    }
}
