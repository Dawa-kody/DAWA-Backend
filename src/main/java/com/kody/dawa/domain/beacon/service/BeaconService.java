package com.kody.dawa.domain.beacon.service;

import com.kody.dawa.domain.beacon.entity.Beacon;

public interface BeaconService {
    boolean isPersonPresent(Beacon signal);
}
