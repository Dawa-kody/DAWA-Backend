package com.kody.dawa.domain.user.presentation;

import com.kody.dawa.domain.user.presentation.dto.request.UserRegisterRequest;
import com.kody.dawa.domain.user.presentation.dto.request.UserUpdateRequest;
import com.kody.dawa.domain.user.presentation.dto.response.UserResponse;
import com.kody.dawa.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/batch")
    public ResponseEntity<String> registerUsers(@RequestBody @Valid List<UserRegisterRequest> requests) {
        userService.registerUsers(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body("사용자 등록 성공");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return users.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdateRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok("사용자 수정 성공");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
