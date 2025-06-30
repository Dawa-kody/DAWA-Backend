package com.kody.dawa.domain.auth.presentation;

import com.kody.dawa.domain.auth.presentation.dto.request.*;
import com.kody.dawa.domain.auth.presentation.dto.response.SignInResponse;
import com.kody.dawa.domain.auth.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmailVerifyService emailVerifyService;
    private final ReissueTokenService reissueTokenService;
    private final SigninService signinService;
    private final SignupService signupStudentService;
    private final PasswordChangeService passwordChangeService;

    @PostMapping("/password/change/email/send")
    public void sendPasswordChangeMail(@RequestBody @Valid EmailCodeRequest request) {
        passwordChangeService.sendMail(request);
    }

    @PostMapping("/password/change/email/verify")
    public void verifyPasswordChangeCode(@RequestBody @Valid PasswordChangeRequest request) {
        passwordChangeService.passwordChange(request);
    }

    @PostMapping("/email/send")
    public void sendSignupMail(@RequestBody @Valid EmailCodeRequest request) {
        emailVerifyService.sendSignupMail(request);
    }

    @PostMapping("/email/verify")
    public void verifyEmailCode(@RequestBody @Valid EmailVerifyCodeRequest request) {
        emailVerifyService.emailVerify(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SigninRequest request, HttpServletResponse response) {
        SignInResponse signInResponse = signinService.execute(request, response);
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/reissue")
    public ResponseEntity<String> refresh(HttpServletRequest request, HttpServletResponse response) {
        reissueTokenService.execute(request, response);
        return ResponseEntity.ok("Access Token 재발급 완료");
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest request) {
        signupStudentService.signup(request);
    }
}
