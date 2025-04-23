package com.kody.dawa.domain.auth.presentation;

import com.kody.dawa.domain.auth.service.EmailVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final EmailVerifyService emailVerifyService;

}
