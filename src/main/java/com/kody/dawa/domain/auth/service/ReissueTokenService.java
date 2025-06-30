package com.kody.dawa.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReissueTokenService {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
