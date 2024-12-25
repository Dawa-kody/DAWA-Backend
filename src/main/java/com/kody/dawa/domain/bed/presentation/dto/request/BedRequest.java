package com.kody.dawa.domain.bed.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BedRequest {
    private boolean bed1;
    private boolean bed2;
}
