package com.kody.dawa.domain.mail.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MailResponse {
    private String content;
    private String item;
    private String count;
    private LocalDateTime createAt;
}
