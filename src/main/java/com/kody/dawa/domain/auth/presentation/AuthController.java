package com.kody.dawa.domain.auth.presentation;

import com.kody.dawa.domain.auth.presentation.dto.request.AuthCodeRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.PwChangeRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.SigninRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.SignupRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.TokenResponse;
import com.kody.dawa.domain.auth.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final SignupService signupService;
    private final SigninService signinService;
    private final RefreshService refreshService;
    private final MailSendService mailSendService;
    private final PwChangeService pwChangeService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signup(@RequestBody @Valid SignupRequest request) {
        TokenResponse response = signupService.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signup(@RequestBody @Valid SigninRequest request) {
        TokenResponse response = signinService.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request){
        String accessToken = request.getHeader("access");
        String refreshToken = request.getHeader("refresh");
        TokenResponse response = refreshService.execute(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping( "/mailsend")
    public void mailSend(@RequestBody @Valid AuthCodeRequest request){
        mailSendService.execute(request);
    }

    @PostMapping("/pwchange")
    public void mailCheck(@RequestBody @Valid PwChangeRequest request) {
        pwChangeService.execute(request);
    }
}
