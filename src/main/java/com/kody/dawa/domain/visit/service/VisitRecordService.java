package com.kody.dawa.domain.visit.service;

import com.kody.dawa.domain.visit.presentation.dto.request.VisitRecordRequest;
import com.kody.dawa.domain.visit.presentation.dto.response.MyVisitRecordResponse;
import com.kody.dawa.domain.visit.presentation.dto.response.VisitRecordsResponse;

import java.util.List;

public interface VisitRecordService {
    void createVisit(VisitRecordRequest request);
    List<MyVisitRecordResponse> getMyVisitRecords();
    List<VisitRecordsResponse> getAllVisitRecords();
}
