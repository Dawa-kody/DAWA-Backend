package com.kody.dawa.domain.excel.service;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelDateRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelStudentRequest;
import org.apache.poi.ss.usermodel.Sheet;

public interface ExcelService {
    void createXssStudent(ExcelStudentRequest request, Sheet sheet);
    void createXssMonth(ExcelDateRequest request, Sheet sheet);
    void createXssYear(ExcelDateRequest request, Sheet sheet);
}
