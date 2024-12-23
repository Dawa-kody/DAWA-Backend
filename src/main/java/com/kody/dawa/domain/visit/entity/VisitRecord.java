package com.kody.dawa.domain.visit.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "visit")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitRecord extends BaseTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String content;
}
