package com.kody.dawa.domain.firstaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private List<Tag> tags = new ArrayList<>();



    @Entity
    @Table(name = "tag")
    @Getter
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
