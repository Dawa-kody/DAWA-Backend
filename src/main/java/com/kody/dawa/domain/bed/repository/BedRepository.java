package com.kody.dawa.domain.bed.repository;

import com.kody.dawa.domain.bed.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {

}