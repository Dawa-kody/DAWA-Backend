package com.kody.dawa.domain.mail.presentation;

import com.kody.dawa.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequestMapping("/mail")
@RestController
@RequiredArgsConstructor
public class MailSseController {
    private final MailService mailService;
    @GetMapping("/sse")
    public SseEmitter sseMail() {
        return mailService.subscribe();
    }
}
