package com.kody.dawa.domain.medicine.presentation.dto;

import com.kody.dawa.domain.medicine.entity.enums.MedicineType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineUpdateRequest {
    @NotNull
    private Long medicineId;
    @NotNull
    private String medicineName;
    private MedicineType medicineType;
    @NotNull
    private Long medicineCount;
}
