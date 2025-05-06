package com.kody.dawa.domain.medicine.entity;

import com.kody.dawa.domain.medicine.entity.enums.MedicineType;
import com.kody.dawa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MedicineType type;

    private Long count;

    private LocalDateTime time;
    @PrePersist
    protected void onCreate() {
        this.time = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.time = LocalDateTime.now();
    }
}
