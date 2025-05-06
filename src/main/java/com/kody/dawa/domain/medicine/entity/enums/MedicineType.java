package com.kody.dawa.domain.medicine.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedicineType {
    COLD_MEDICINE("감기약"),
    PAINKILLER("진통제"),
    GENERAL_MEDICINE("일반약");

    private final String Name;
}
