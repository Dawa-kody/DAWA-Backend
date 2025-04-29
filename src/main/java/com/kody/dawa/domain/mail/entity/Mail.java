package com.kody.dawa.domain.mail.entity;

import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail extends BaseTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String content;

    private String item;

    private String count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
