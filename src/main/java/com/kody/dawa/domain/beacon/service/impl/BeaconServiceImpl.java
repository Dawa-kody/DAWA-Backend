package com.kody.dawa.domain.beacon.service.impl;

import com.kody.dawa.domain.beacon.entity.Beacon;
import com.kody.dawa.domain.beacon.repository.BeaconRepository;
import com.kody.dawa.domain.beacon.service.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeaconServiceImpl implements BeaconService {
    private static final int RSSI_THRESHOLD = -70;
    private final BeaconRepository beaconRepository;
    public boolean isPersonPresent(Beacon signal) {
        boolean isHere = signal.getRssi() > RSSI_THRESHOLD;

        Optional<Beacon> existingBeaconOpt = beaconRepository.findByUuid(signal.getUuid());
        Beacon beacon;
        if (existingBeaconOpt.isPresent()) {
            beacon = existingBeaconOpt.get();
            beacon.setRssi(signal.getRssi());
            beacon.setIsHere(isHere);
        } else {
            beacon = Beacon.builder()
                    .uuid(signal.getUuid())
                    .major(signal.getMajor())
                    .minor(signal.getMinor())
                    .rssi(signal.getRssi())
                    .isHere(isHere)
                    .build();
        }
        beaconRepository.save(beacon);

        return isHere;
    }
}
