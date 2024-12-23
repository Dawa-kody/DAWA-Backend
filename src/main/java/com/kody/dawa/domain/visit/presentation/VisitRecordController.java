package com.kody.dawa.domain.visit.presentation;

import com.kody.dawa.domain.visit.entity.VisitRecord;
import com.kody.dawa.domain.visit.presentation.dto.request.VisitRecordRequest;
import com.kody.dawa.domain.visit.presentation.dto.response.VisitRecordResponse;
import com.kody.dawa.domain.visit.service.VisitRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/visit")
@RestController
@RequiredArgsConstructor
public class VisitRecordController {
    private final VisitRecordService visitRecordService;

    @PostMapping("/write")
    public void createRecord(@RequestBody VisitRecordRequest request) {
        visitRecordService.createVisit(request);
    }

    @GetMapping
    public List<VisitRecordResponse> getMyRecords() {
        return visitRecordService.getMyVisitRecords();
    }

    @GetMapping("/allRecord")
    public List<VisitRecordResponse> getAllRecord() {
        return visitRecordService.getAllVisitRecords();
    }
}
