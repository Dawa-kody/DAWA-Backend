package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.AuthCodeRequest;

public interface MailSendService {
    void execute(AuthCodeRequest request);
}
