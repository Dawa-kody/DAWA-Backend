package com.kody.dawa.domain.firstaid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "firstaid")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirstAid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "firstAid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    private String content;

    private String diseaseName;

    private String title;

    private String emoji;

    @Entity
    @Table(name = "tag")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tag {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "firstaid_id")
        private FirstAid firstAid;
    }
}
