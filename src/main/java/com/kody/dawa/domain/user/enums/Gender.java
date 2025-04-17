package com.kody.dawa.domain.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MAN("남성", "남"),
    WOMAN("여성", "여");

    private final String name;       // DB/응답용 (남성, 여성)
    private final String shortName;  // 통계용 키 (남, 여)

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}

