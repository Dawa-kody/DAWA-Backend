package com.kody.dawa.domain.user.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HealthIssueDetail {
    private String allergyImmune;        // 알레르기 및 면역 관련 질환
    private String chronicMedication;    // 만성 질환 및 약물관리 필요
    private String emergencyPossible;    // 응급 상황 발생 가능 질환
    private String etc;                  // 기타
}
