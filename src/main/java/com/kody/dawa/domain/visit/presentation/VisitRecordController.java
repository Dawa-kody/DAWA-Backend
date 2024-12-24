package com.kody.dawa.domain.visit.presentation;

import com.kody.dawa.domain.visit.presentation.dto.request.VisitMyRecordRequest;
import com.kody.dawa.domain.visit.presentation.dto.response.MyVisitRecordResponse;
import com.kody.dawa.domain.visit.presentation.dto.response.VisitRecordsResponse;
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
    public void createRecord(@RequestBody VisitMyRecordRequest request) {
        visitRecordService.createMyVisit(request);
    }
    @GetMapping
    public List<MyVisitRecordResponse> getMyRecords() {
        return visitRecordService.getMyVisitRecords();
    }
    @GetMapping("/allRecord")
    public List<VisitRecordsResponse> getAllRecord() {
        return visitRecordService.getAllVisitRecords();
    }
}
