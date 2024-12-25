package com.kody.dawa.domain.beacon.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "beacon")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beacon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private int major;
    private int minor;
    private int rssi;
    private boolean isHere;

    public void setIsHere(boolean isHere) {
        this.isHere = isHere;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
