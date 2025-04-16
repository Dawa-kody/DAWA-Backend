package com.kody.dawa.domain.questionnaire.entity;

import com.kody.dawa.global.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "questionnaire")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistics extends BaseTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


}
