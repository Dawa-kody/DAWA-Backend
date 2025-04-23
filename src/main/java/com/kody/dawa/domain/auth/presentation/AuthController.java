package com.kody.dawa.domain.auth.presentation;

import com.kody.dawa.domain.auth.presentation.dto.request.*;
import com.kody.dawa.domain.auth.presentation.dto.response.ReissueTokenResponse;
import com.kody.dawa.domain.auth.presentation.dto.response.SignInResponse;
import com.kody.dawa.domain.auth.service.*;
import com.kody.dawa.global.security.jwt.JwtProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmailVerifyService emailVerifyService;
    private final ReissueTokenService reissueTokenService;
    private final SigninService signinService;
    private final SignupService signupStudentService;
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

    @PostMapping("/reissue")
    public ReissueTokenResponse reissueToken(@RequestHeader("Refresh-Token") String refreshHeader) {
        String refreshToken = jwtProvider.resolveToken(refreshHeader);
        return reissueTokenService.execute(refreshToken);
    }

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody @Valid SigninRequest request) {
        return signinService.execute(request);
    }

    @PostMapping("/signup/student")
    public void signupStudent(@RequestBody @Valid SignupRequest request) {
        signupStudentService.signupStudent(request);
    }
}
