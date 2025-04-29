package com.kody.dawa.domain.notice.presentation;

import com.kody.dawa.domain.notice.presentation.dto.request.NoticeWriteRequest;
import com.kody.dawa.domain.notice.presentation.dto.response.NoticeResponse;
import com.kody.dawa.domain.notice.presentation.dto.response.NoticesResponse;
import com.kody.dawa.domain.notice.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> writeNotice(@RequestBody @Valid NoticeWriteRequest request) {
        noticeService.writeNotice(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<NoticesResponse>> getAllNotices() {
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getNoticeById(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }
}
