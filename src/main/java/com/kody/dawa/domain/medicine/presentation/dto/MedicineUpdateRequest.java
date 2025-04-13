package com.kody.dawa.domain.medicine.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineUpdateRequest {
    private Long medicineId;
    private String medicineName;
    private String medicineType;
    private Long medicineCount;
}
