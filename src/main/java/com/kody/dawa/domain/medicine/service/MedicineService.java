package com.kody.dawa.domain.medicine.service;

import com.kody.dawa.domain.medicine.entity.Medicine;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineDeleteRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineUpdateRequest;

import java.util.List;

public interface MedicineService {
    Medicine createMedicine(MedicineRequest request);
    Medicine updateMedicine(MedicineUpdateRequest request);
    void deleteMedicine(MedicineDeleteRequest request);
    List<Medicine> getAllMedicine();
}
