package com.kody.dawa.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponse {
    private String title;
    private String content;
    private String yearMonthDay;
}
