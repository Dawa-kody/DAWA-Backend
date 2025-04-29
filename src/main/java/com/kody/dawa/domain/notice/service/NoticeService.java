package com.kody.dawa.domain.notice.service;

import com.kody.dawa.domain.notice.presentation.dto.request.NoticeWriteRequest;
import com.kody.dawa.domain.notice.presentation.dto.response.NoticeResponse;
import com.kody.dawa.domain.notice.presentation.dto.response.NoticesResponse;

import java.util.List;

public interface NoticeService {
    void writeNotice(NoticeWriteRequest request);
    List<NoticesResponse> getAllNotices();
    NoticeResponse getNoticeById(Long id);
    void deleteNotice(Long id);
}
