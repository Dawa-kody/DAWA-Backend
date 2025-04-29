package com.kody.dawa.domain.mail.repository;

import com.kody.dawa.domain.mail.entity.Mail;
import com.kody.dawa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail,Long> {
    @Query("SELECT m FROM Mail m WHERE m.user = :user ORDER BY m.createAt DESC")
    List<Mail> findAllByUserOrderByCreateAtDesc(@Param("user") User user);
}
