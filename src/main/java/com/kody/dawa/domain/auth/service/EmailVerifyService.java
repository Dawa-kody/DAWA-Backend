package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.EmailVerifyCodeRequest;

public interface EmailVerifyService {
    void sendSignupMail(EmailCodeRequest request); //메일 보내기

    void emailVerify(EmailVerifyCodeRequest request); //메일 인증
}
