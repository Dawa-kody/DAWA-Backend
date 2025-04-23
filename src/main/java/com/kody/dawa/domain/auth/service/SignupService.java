package com.kody.dawa.domain.auth.service;


import com.kody.dawa.domain.auth.presentation.dto.request.SignupRequest;

public interface SignupService {
    void signupStudent(SignupRequest request);
}
