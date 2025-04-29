package com.kody.dawa.domain.mail.presentation;

import com.kody.dawa.domain.mail.presentation.dto.response.MailResponse;
import com.kody.dawa.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RequestMapping("/mail")
@RestController
@RequiredArgsConstructor
public class MailSseController {
    private final MailService mailService;
    @GetMapping("/sse")
    public ResponseEntity<SseEmitter> sseMail() {
        SseEmitter emitter = mailService.subscribe();
        return ResponseEntity.ok(emitter);
    }

    @GetMapping
    public ResponseEntity<List<MailResponse>> getUserMails() {
        List<MailResponse> mailList = mailService.getUserMails();
        return ResponseEntity.ok(mailList);
    }
}
