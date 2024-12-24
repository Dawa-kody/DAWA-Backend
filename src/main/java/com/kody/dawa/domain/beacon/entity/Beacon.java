package com.kody.dawa.domain.beacon.entity;

import lombok.Data;

@Data
public class Beacon {
    private String uuid;
    private int major;
    private int minor;
    private int rssi;
}
