package com.kody.dawa.domain.firstaid.service;

import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidResponse;

import java.util.List;

public interface FirstAidService {
    void createFirstAid(FirstAidRequest request);

    List<FirstAidResponse> getFirstAids(List<String> tags);
}
