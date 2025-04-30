package com.kody.dawa.domain.excel.service;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelDateRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelStudentRequest;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public interface ExcelService {
//    void createXssStudent(ExcelStudentRequest request, Sheet sheet);
//    void createXssMonth(ExcelDateRequest request, Sheet sheet);
//    void createXssYear(ExcelDateRequest request, Sheet sheet);
    void createXss(Workbook workbook);
    void createDate(Workbook workbook,String date);
    List<Map<String,String>> change(Workbook workbook, List<Map<String, String>> result);
}
