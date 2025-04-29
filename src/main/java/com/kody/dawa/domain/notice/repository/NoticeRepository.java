package com.kody.dawa.domain.notice.repository;

import com.kody.dawa.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n ORDER BY n.createAt DESC")
    List<Notice> findAllByOrderByCreateAtDesc();
}
