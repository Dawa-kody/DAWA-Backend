package com.kody.dawa.domain.notice.repository;

import com.kody.dawa.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
