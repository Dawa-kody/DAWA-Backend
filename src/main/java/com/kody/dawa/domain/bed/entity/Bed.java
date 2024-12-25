package com.kody.dawa.domain.bed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "bed")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bed {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private boolean bed1;

    private boolean bed2;

    public void setBed1(boolean bed1) {
        this.bed1 = bed1;
    }

    public void setBed2(boolean bed2) {
        this.bed2 = bed2;
    }
}
