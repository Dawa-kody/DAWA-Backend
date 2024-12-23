package com.kody.dawa.domain.visit.service.impl;

import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.visit.entity.VisitRecord;
import com.kody.dawa.domain.visit.presentation.dto.request.VisitRecordRequest;
import com.kody.dawa.domain.visit.presentation.dto.response.VisitRecordResponse;
import com.kody.dawa.domain.visit.repository.VisitRecordRepository;
import com.kody.dawa.domain.visit.service.VisitRecordService;
import com.kody.dawa.global.service.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitRecordServiceImpl implements VisitRecordService {
    private final VisitRecordRepository visitRecordRepository;
    private final GetUser getUser;

    public void createVisit(VisitRecordRequest request) {
        User user = getUser.getCurrentUser();
        VisitRecord visitRecord = VisitRecord.builder()
                .content(request.getContent())
                .user(user)
                .build();
        visitRecordRepository.save(visitRecord);
    }

    public List<VisitRecordResponse> getMyVisitRecords() {
        User user = getUser.getCurrentUser();
        List<VisitRecord> visitRecords =visitRecordRepository.findVisitRecordsByOrderBycreateAtDesc(user);
        return visitRecords.stream()
                .map(visitRecord -> VisitRecordResponse.builder()
                            .content(visitRecord.getContent())
                            .createdAt(visitRecord.getCreateAt())
                            .build())
                .collect(Collectors.toList());
    }

    public List<VisitRecordResponse> getAllVisitRecords() {
        List<VisitRecord> visitRecords =visitRecordRepository.findAll();
        return visitRecords.stream()
                .map(visitRecord -> VisitRecordResponse.builder()
                        .content(visitRecord.getContent())
                        .createdAt(visitRecord.getCreateAt())
                        .name(visitRecord.getUser().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
