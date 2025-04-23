package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.EmailCodeRequest;
import com.kody.dawa.domain.auth.presentation.dto.request.PasswordChangeRequest;

public interface PasswordChangeService {
    void sendMail(EmailCodeRequest request); //메일 전송

    void passwordChange(PasswordChangeRequest request); //메일 인증
}
