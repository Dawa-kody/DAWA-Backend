package com.kody.dawa.domain.auth.presentation;

import com.kody.dawa.domain.auth.presentation.dto.request.SignupRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.TokenResponse;
import com.kody.dawa.domain.auth.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignupService signupService;

    @PostMapping
    public ResponseEntity<TokenResponse> signup(@RequestBody @Valid SignupRequest request) {
        TokenResponse response = signupService.execute(request);
        return ResponseEntity.ok(response);
    }

}
