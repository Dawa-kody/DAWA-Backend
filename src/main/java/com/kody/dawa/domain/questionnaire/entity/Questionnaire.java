package com.kody.dawa.domain.questionnaire.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "questionnaire")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Questionnaire extends BaseTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String serialNumber;

    private String userName;

    private String gender;

    private String division;

    @Column(length = 4)
    private String schoolNumber;

    private String disease; //증상

    private String treatment; //처치상황 (투약 또는 처치)

    private Long quantity; //수량

    private String medication1; //투약

    private Long quantity1; //수량1

    private String medication2; //투약

    private Long quantity2; //수량2

    private String notes; //비고
}
