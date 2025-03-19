package com.kody.dawa.domain.medicine.presentation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineUpdateRequest {
    private String medicineName;
    private int medicineCount;
}
