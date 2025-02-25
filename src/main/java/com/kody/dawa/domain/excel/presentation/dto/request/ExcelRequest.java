package com.kody.dawa.domain.excel.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelRequest {
    private String fileName;
    private String gender;
    private Treatment treatment;
    private Date date;
}
