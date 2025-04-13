package com.kody.dawa.domain.medicine.service.impl;

import com.kody.dawa.domain.medicine.entity.Medicine;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineDeleteRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineUpdateRequest;
import com.kody.dawa.domain.medicine.repository.MedicineRepository;
import com.kody.dawa.domain.medicine.service.MedicineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    public Medicine createMedicine(MedicineRequest request) {
        if(medicineRepository.findByName(request.getMedicineName()) != null){
            throw new IllegalArgumentException("이미 추가된 약입니다.");
        }
        Medicine medicine = Medicine.builder()
                .name(request.getMedicineName())
                .type(request.getMedicineType())
                .count(request.getMedicineCount())
                .build();
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicine(MedicineUpdateRequest request) {
        Medicine medicine = medicineRepository.findById(request.getMedicineId())
                .orElseThrow(() -> new RuntimeException("없는 약입니다"));

        medicine.setCount(request.getMedicineCount());
        medicine.setType(request.getMedicineType());
        medicine.setName(request.getMedicineName());

        medicineRepository.save(medicine);
        return medicine;
    }

    @Transactional
    public void deleteMedicine(MedicineDeleteRequest request) {
        if(medicineRepository.findByName(request.getMedicineName()) == null){
            throw new IllegalArgumentException("추가되지 않는 약입니다.");
        }
        medicineRepository.deleteByName(request.getMedicineName());
    }

    public List<Medicine> getAllMedicine(String medicineType) {
        if (medicineType == null || medicineType.isBlank()) {
            return medicineRepository.findAll();
        }
        return medicineRepository.findByType(medicineType);
    }
}