package com.kody.dawa.domain.mail.service;

import com.kody.dawa.domain.mail.entity.Mail;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface MailService {
    void send(String schoolNumber, Mail mail);
    SseEmitter subscribe();
}
