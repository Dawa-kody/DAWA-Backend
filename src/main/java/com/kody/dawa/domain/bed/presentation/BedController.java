package com.kody.dawa.domain.bed.presentation;

import com.kody.dawa.domain.bed.entity.Bed;
import com.kody.dawa.domain.bed.presentation.dto.request.BedRequest;
import com.kody.dawa.domain.bed.service.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/bed")
@RestController
@RequiredArgsConstructor
public class BedController {
    private final BedService bedService;

    @PostMapping //admin
    public ResponseEntity<Void> bedSetting(@RequestBody BedRequest request) {
        bedService.bedSetting(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Bed> getBedStatus() {
        Bed currentBed = bedService.getCurrentBedStatus();
        return ResponseEntity.ok(currentBed);
    }
}
