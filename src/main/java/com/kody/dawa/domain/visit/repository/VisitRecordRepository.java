package com.kody.dawa.domain.visit.repository;

import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.visit.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    @Query(value = "SELECT v FROM VisitRecord v ORDER BY v.createAt DESC")
    List<VisitRecord> findVisitRecordsByOrderBycreateAtDesc(User user);
    @Query(value = "SELECT v FROM VisitRecord v ORDER BY v.createAt DESC")
    List<VisitRecord> findVisitRecordsByOrderBycreateAtDesc();

}
