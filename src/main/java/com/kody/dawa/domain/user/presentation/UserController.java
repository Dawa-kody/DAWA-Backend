package com.kody.dawa.domain.user.presentation;

import com.kody.dawa.domain.user.presentation.dto.request.UserRegisterRequest;
import com.kody.dawa.domain.user.presentation.dto.request.UserUpdateRequest;
import com.kody.dawa.domain.user.presentation.dto.response.UserHealthIssueDetailResponse;
import com.kody.dawa.domain.user.presentation.dto.response.UserResponse;
import com.kody.dawa.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/batch") //admin
    public ResponseEntity<String> registerUsers(@RequestBody @Valid List<UserRegisterRequest> requests) {
        userService.registerUsers(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body("사용자 등록 성공");
    }

    @GetMapping("/users") //admin
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(required = false) List<String> classes,
            @RequestParam(required = false) String name
    ) {
        List<UserResponse> users = userService.getAllUsers(classes, name);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}") //admin
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdateRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok("사용자 수정 성공");
    }

    @PostMapping("/healthissues/{schoolNumber}") //admin
    public ResponseEntity<UserHealthIssueDetailResponse> getUserHealthIssues(@PathVariable String schoolNumber) {
        UserHealthIssueDetailResponse response = userService.getHealthIssueDetail(schoolNumber);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}") //admin
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
