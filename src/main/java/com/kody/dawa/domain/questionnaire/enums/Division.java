package com.kody.dawa.domain.questionnaire.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Division {
    RESPIRATORY_SYSTEM("호흡기계"),
    DIGESTIVE_SYSTEM("소화기계"),
    CIRCULATORY_SYSTEM("순환기계"),
    NERVOUS_SYSTEM("정신신경계"),
    MUSCULOSKELETAL_SYSTEM("근골격계"),
    INTEGUMENTARY_SYSTEM("피부피하계"),
    UROGENITAL_SYSTEM("비뇨생식기계"),
    DENTAL_SYSTEM("구강치아계"),
    OTORHINOLARYNGOLOGY("이비인후과계"),
    OPHTHALMOLOGY_SYSTEM("안과계"),
    INFECTIOUS_DISEASE("감염병"),
    MENTAL_COUNSELING("상담"),
    OTHER("기타");

    private final String name;
}
