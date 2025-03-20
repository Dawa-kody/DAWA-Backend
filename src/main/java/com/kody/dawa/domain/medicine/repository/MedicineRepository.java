package com.kody.dawa.domain.medicine.repository;

import com.kody.dawa.domain.medicine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByName(String name);
    void deleteByName(String name);
    List<Medicine> findByType(String type);
}
