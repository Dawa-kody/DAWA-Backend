package com.kody.dawa.domain.auth.service;


import com.kody.dawa.domain.auth.presentation.dto.request.SignupStudentRequest;

public interface SignupStudentService {
    void signupStudent(SignupStudentRequest request);
}
