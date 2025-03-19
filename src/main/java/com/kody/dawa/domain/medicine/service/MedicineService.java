package com.kody.dawa.domain.medicine.service;

import com.kody.dawa.domain.medicine.entity.Medicine;
import com.kody.dawa.domain.medicine.presentation.domain.MedicineRequest;
import com.kody.dawa.domain.medicine.presentation.domain.MedicineUpdateRequest;

public interface MedicineService {
    Medicine createMedicine(MedicineRequest request);
    Medicine updateMedicine(MedicineUpdateRequest request);
}
