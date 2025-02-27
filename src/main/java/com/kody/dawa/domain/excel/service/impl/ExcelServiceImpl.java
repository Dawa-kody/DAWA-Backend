package com.kody.dawa.domain.excel.service.impl;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelDateRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelStudentRequest;
import com.kody.dawa.domain.excel.repository.ExcelRepository;
import com.kody.dawa.domain.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final ExcelRepository excelRepository;

    public void createXssStudent(ExcelStudentRequest excelRequest, Sheet sheet){
        createHeader(sheet);
        String studentId = excelRequest.getStudentId();

    }

    public void createXssMonth(ExcelDateRequest excelRequest, Sheet sheet){
        createHeader(sheet);
        Date date =  excelRequest.getDate();
    }

    public void createXssYear(ExcelDateRequest excelRequest, Sheet sheet){
        createHeader(sheet);
        Date date =  excelRequest.getDate();
    }

    public void createHeader(Sheet sheet){
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