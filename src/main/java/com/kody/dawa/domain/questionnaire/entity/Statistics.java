package com.kody.dawa.domain.questionnaire.entity;

import com.kody.dawa.domain.questionnaire.enums.Division;
import jakarta.persistence.*;
import lombok.*;
import com.kody.dawa.domain.user.enums.Gender;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateType;

    private String date;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Division division;

    private Long count;
}
