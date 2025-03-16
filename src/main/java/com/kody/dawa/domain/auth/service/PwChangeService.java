package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.PwChangeRequest;

import java.util.HashMap;

public interface PwChangeService {
    void passwordChange(PwChangeRequest request);
}
