package com.kody.dawa.domain.excel.service;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelRequest;
import org.apache.poi.ss.usermodel.Sheet;

public interface ExcelService {
    void createXssStudent(ExcelRequest request, Sheet sheet);
}
