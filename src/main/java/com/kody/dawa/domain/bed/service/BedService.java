package com.kody.dawa.domain.bed.service;

import com.kody.dawa.domain.bed.entity.Bed;
import com.kody.dawa.domain.bed.presentation.dto.request.BedRequest;

public interface BedService {
    void bedSetting(BedRequest request);
    Bed getCurrentBedStatus();
}
