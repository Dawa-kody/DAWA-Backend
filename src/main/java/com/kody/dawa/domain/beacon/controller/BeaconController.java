package com.kody.dawa.domain.beacon.controller;

import com.kody.dawa.domain.beacon.entity.Beacon;
import com.kody.dawa.domain.beacon.service.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/beacon")
@RestController
@RequiredArgsConstructor
public class BeaconController {
    private final BeaconService beaconService;

    @PostMapping
    public ResponseEntity<Boolean> checkPresence(@RequestBody Beacon signal) {
        boolean isPresent = beaconService.isPersonPresent(signal);
        return ResponseEntity.ok(isPresent);
    }
}
