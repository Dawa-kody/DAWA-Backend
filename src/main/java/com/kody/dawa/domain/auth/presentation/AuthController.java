package com.kody.dawa.domain.auth.presentation;

import com.kody.dawa.domain.auth.presentation.dto.request.*;
import com.kody.dawa.domain.auth.presentation.dto.response.ReissueTokenResponse;
import com.kody.dawa.domain.auth.presentation.dto.response.SignInResponse;
import com.kody.dawa.domain.auth.service.*;
import com.kody.dawa.global.security.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmailVerifyService emailVerifyService;
    private final ReissueTokenService reissueTokenService;
    private final SigninService signinService;
    private final SignupService signupService;
    private final JwtProvider jwtProvider;
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
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SigninRequest request) {
        SignInResponse signInResponse = signinService.execute(request);
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/reissue")
    public ReissueTokenResponse reissueToken(@RequestHeader("Refresh-Token") String refreshHeader) {
        String refreshToken = jwtProvider.resolveToken(refreshHeader);
        return reissueTokenService.execute(refreshToken);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest request) {
        signupService.signup(request);
    }
}
