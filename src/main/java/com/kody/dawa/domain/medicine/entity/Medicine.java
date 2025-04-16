package com.kody.dawa.domain.medicine.entity;

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
public class Medicine /*extends BaseTime*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long count;

    //순환기계 근골격계 이런 계류
//    private String body_system;

    //진통제, 감기약, 이런 약류
    private String type;

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
