package com.kody.dawa.domain.mail.repository;

import com.kody.dawa.domain.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail,Long> {
}
