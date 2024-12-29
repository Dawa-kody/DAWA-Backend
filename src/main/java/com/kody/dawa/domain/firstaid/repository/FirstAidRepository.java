package com.kody.dawa.domain.firstaid.repository;

import com.kody.dawa.domain.firstaid.entity.FirstAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstAidRepository extends JpaRepository<FirstAid, Long> {

    @Query("SELECT DISTINCT f FROM FirstAid f JOIN f.tags t WHERE t.name IN :tags")
    List<FirstAid> findByTags(List<String> tags);
}
