package com.kody.dawa.domain.visit.service.impl;

import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.visit.entity.VisitRecord;
import com.kody.dawa.domain.visit.presentation.dto.request.VisitMyRecordRequest;
import com.kody.dawa.domain.visit.presentation.dto.response.MyVisitRecordResponse;
import com.kody.dawa.domain.visit.presentation.dto.response.VisitRecordsResponse;
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

    public void createMyVisit(VisitMyRecordRequest request) {
        User user = getUser.getCurrentUser();
        VisitRecord visitRecord = VisitRecord.builder()
                .content(request.getContent())
                .user(user)
                .build();
        visitRecordRepository.save(visitRecord);
    }

    public List<MyVisitRecordResponse> getMyVisitRecords() {
        User user = getUser.getCurrentUser();
        List<VisitRecord> visitRecords =visitRecordRepository.findVisitRecordsByOrderBycreateAtDesc(user);
        return visitRecords.stream()
                .map(visitRecord -> MyVisitRecordResponse.builder()
                            .content(visitRecord.getContent())
                            .formattedDate(visitRecord.getFormattedDate())
                            .dayOfWeek(visitRecord.getDayOfWeek())
                            .build())
                .collect(Collectors.toList());
    }

    public List<VisitRecordsResponse> getAllVisitRecords() {
        List<VisitRecord> visitRecords =visitRecordRepository.findVisitRecordsByOrderBycreateAtDesc();
        return visitRecords.stream()
                .map(visitRecord -> VisitRecordsResponse.builder()
                        .content(visitRecord.getContent())
                        .name(visitRecord.getUser().getName())
                        .name(visitRecord.getUser().getRoles().toString())
                        .formattedDate(visitRecord.getFormattedDate())
                        .dayOfWeek(visitRecord.getDayOfWeek())
                        .build())
                .collect(Collectors.toList());
    }
}
