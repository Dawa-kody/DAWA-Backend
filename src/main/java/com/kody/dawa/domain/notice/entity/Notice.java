package com.kody.dawa.domain.notice.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
