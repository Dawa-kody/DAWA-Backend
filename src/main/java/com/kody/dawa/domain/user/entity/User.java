package com.kody.dawa.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kody.dawa.domain.mail.entity.Mail;
import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import com.kody.dawa.domain.rental.entity.Rental;
import com.kody.dawa.domain.user.enums.Gender;
import com.kody.dawa.domain.user.enums.Role;
import com.kody.dawa.domain.user.util.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "user")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String name;

    private String schoolNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private HealthIssueDetail healthIssueDetail;

    @Column(name = "email_verify_status", columnDefinition = "TINYINT(1)")
    private boolean emailVerifyStatus;

    @Convert(converter = StringListConverter.class)
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Questionnaire> questionnaire = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Rental> rentals = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Mail> mails = new HashSet<>();
}