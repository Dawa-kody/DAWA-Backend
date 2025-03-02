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

    @Column(length = 4)
    private String SchoolNumber;

    private String disease;

    private String content;

    private String time;
}
