package com.kody.dawa.domain.mail.service;

import com.kody.dawa.domain.mail.entity.Mail;
import com.kody.dawa.domain.mail.presentation.dto.response.MailResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface MailService {
    void send(String schoolNumber, Mail mail);
    SseEmitter subscribe();
    List<MailResponse> getUserMails();
}
