package com.kody.dawa.domain.notice.service.impl;

import com.kody.dawa.domain.notice.entity.Notice;
import com.kody.dawa.domain.notice.presentation.dto.request.NoticeWriteRequest;
import com.kody.dawa.domain.notice.presentation.dto.response.NoticeResponse;
import com.kody.dawa.domain.notice.presentation.dto.response.NoticesResponse;
import com.kody.dawa.domain.notice.repository.NoticeRepository;
import com.kody.dawa.domain.notice.service.NoticeService;
import com.kody.dawa.global.exception.HttpException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void writeNotice(NoticeWriteRequest request) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        noticeRepository.save(notice);
    }

    @Transactional
    public List<NoticesResponse> getAllNotices() {
        return noticeRepository.findAllByOrderByCreateAtDesc().stream()
                .map(notices -> new NoticesResponse(
                        notices.getId(),
                        notices.getTitle(),
                        notices.getYearMonthDay()))
                .collect(Collectors.toList());
    }

    @Transactional
    public NoticeResponse getNoticeById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "공지사항을 찾을 수 없습니다."));
        return new NoticeResponse(notice.getTitle(), notice.getContent(), notice.getYearMonthDay());
    }

    @Transactional
    public void deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "공지사항을 찾을 수 없습니다."));
        noticeRepository.delete(notice);
    }
}
