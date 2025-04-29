package com.kody.dawa.domain.medicine.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {
    @NotNull
    private String medicineName;
    @NotNull
    private Long medicineCount;
}
