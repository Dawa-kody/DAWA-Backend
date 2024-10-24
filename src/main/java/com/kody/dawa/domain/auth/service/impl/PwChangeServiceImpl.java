package com.kody.dawa.domain.auth.service.impl;

import com.kody.dawa.domain.auth.presentation.dto.request.PwChangeRequest;
import com.kody.dawa.domain.auth.repository.AuthCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PwChangeServiceImpl {
    private final AuthCodeRepository authCodeRepository;
    public void execute(PwChangeRequest request) {

    }
}
