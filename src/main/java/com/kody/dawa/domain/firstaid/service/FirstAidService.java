package com.kody.dawa.domain.firstaid.service;

import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidResponse;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidsResponse;

import java.util.List;

public interface FirstAidService {
    void createFirstAid(FirstAidRequest request);

    List<FirstAidsResponse> getFirstAids(List<String> tags);

    FirstAidResponse getFirstAid(Long id);
}
