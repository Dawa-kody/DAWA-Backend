package com.kody.dawa.domain.rental.entity;

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

@Table(name = "rental")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rental extends BaseTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private boolean isAccepted;

    private String rental;

    private String count;

    private boolean isRentaled;

    public void setIsRentaled(boolean isRentaled) {
        this.isRentaled = isRentaled;
    }
}
