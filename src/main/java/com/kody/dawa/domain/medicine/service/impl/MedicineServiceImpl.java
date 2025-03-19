package com.kody.dawa.domain.medicine.service.impl;

import com.kody.dawa.domain.medicine.entity.Medicine;
import com.kody.dawa.domain.medicine.presentation.domain.MedicineRequest;
import com.kody.dawa.domain.medicine.presentation.domain.MedicineUpdateRequest;
import com.kody.dawa.domain.medicine.repository.MedicineRepository;
import com.kody.dawa.domain.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    public Medicine createMedicine(MedicineRequest request) {
        Medicine medicine = Medicine.builder()
                .name(request.getMedicineName())
                .type(request.getMedicineType())
                .count(request.getMedicineCount())
                .build();
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicine(MedicineUpdateRequest request) {
        return medicineRepository.save(medicineRepository.findByName(request.getMedicineName()).toBuilder()
                .count(request.getMedicineCount())
                .build());
    }
}