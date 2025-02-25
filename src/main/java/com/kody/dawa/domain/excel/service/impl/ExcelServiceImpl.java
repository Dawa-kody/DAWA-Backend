package com.kody.dawa.domain.excel.service.impl;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelRequest;
import com.kody.dawa.domain.excel.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl implements ExcelService {
    public void createXssStudent(ExcelRequest excelRequest, Sheet sheet){
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("연번");
        header.createCell(1).setCellValue("이름");
        header.createCell(2).setCellValue("학번");
        header.createCell(3).setCellValue("성별");
        header.createCell(4).setCellValue("병명");
        header.createCell(5).setCellValue("처치");
        header.createCell(6).setCellValue("시간");


    }
}
