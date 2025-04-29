package com.kody.dawa.domain.mail.service.impl;

import com.kody.dawa.domain.mail.entity.Mail;
import com.kody.dawa.domain.mail.presentation.dto.response.MailResponse;
import com.kody.dawa.domain.mail.repository.MailRepository;
import com.kody.dawa.domain.mail.service.MailService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.service.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final GetUser getUser;
    private final MailRepository mailRepository;

    public SseEmitter subscribe() {
        User user = getUser.getCurrentUser();
        String schoolNumber = user.getSchoolNumber();

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(schoolNumber, emitter);

        emitter.onCompletion(() -> emitters.remove(schoolNumber));
        emitter.onTimeout(() -> emitters.remove(schoolNumber));

        return emitter;
    }

    public void send(String schoolNumber, Mail mail) {
        SseEmitter emitter = emitters.get(schoolNumber);
        if (emitter != null) {
            try {
                MailResponse response = new MailResponse(
                        mail.getContent(),
                        mail.getItem(),
                        mail.getCount(),
                        mail.getYearMonthDay()
                );
                emitter.send(SseEmitter.event()
                        .name("mail")
                        .data(response));
            } catch (IOException e) {
                emitters.remove(schoolNumber);
            }
        }
    }

    public List<MailResponse> getUserMails() {
        User user = getUser.getCurrentUser();
        List<Mail> mails = mailRepository.findAllByUserOrderByCreateAtDesc(user);
        return mails.stream()
                .map(mail -> new MailResponse(
                        mail.getContent(),
                        mail.getItem(),
                        mail.getCount(),
                        mail.getYearMonthDay()
                ))
                .toList();
    }
}
