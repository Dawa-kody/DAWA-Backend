package com.kody.dawa.domain.medicine.presentation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {
    private String medicineName;
    private String medicineType;
    private int medicineCount;
}
