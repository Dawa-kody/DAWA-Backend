package com.kody.dawa.domain.notice.entity;

import com.kody.dawa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;
}
