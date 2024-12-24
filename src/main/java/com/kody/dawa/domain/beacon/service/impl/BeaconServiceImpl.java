package com.kody.dawa.domain.beacon.service.impl;

import com.kody.dawa.domain.beacon.entity.Beacon;
import com.kody.dawa.domain.beacon.service.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeaconServiceImpl implements BeaconService {
    private static final int RSSI_THRESHOLD = -70;
    public boolean isPersonPresent(Beacon signal) {
        return signal.getRssi() > RSSI_THRESHOLD;
    }
}
