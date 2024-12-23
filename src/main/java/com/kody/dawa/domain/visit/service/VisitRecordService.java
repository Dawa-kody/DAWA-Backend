package com.kody.dawa.domain.visit.service;

import com.kody.dawa.domain.visit.presentation.dto.request.VisitRecordRequest;
import com.kody.dawa.domain.visit.presentation.dto.response.VisitRecordResponse;

import java.util.List;

public interface VisitRecordService {
    void createVisit(VisitRecordRequest request);
    List<VisitRecordResponse> getMyVisitRecords();
    List<VisitRecordResponse> getAllVisitRecords();
}
