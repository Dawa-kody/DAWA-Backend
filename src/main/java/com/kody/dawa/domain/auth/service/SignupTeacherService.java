package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.SignupTeacherRequest;

public interface SignupTeacherService {
    void signupTeacher(SignupTeacherRequest request);
}
