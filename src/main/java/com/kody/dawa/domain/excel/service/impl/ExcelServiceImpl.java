package com.kody.dawa.domain.excel.service.impl;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelDateRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelStudentRequest;
import com.kody.dawa.domain.excel.repository.ExcelRepository;
import com.kody.dawa.domain.excel.service.ExcelService;
import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final ExcelRepository excelRepository;

    public void createXssStudent(ExcelStudentRequest excelRequest, Sheet sheet){
        createHeader(sheet);
        String studentId = excelRequest.getStudentId();
        List<Questionnaire> questionnaires = excelRepository.findBySchoolNumber(studentId);
        createRow(questionnaires, sheet);
    }

    public void createXssMonth(ExcelDateRequest excelRequest, Sheet sheet){
        createHeader(sheet);
        String date =  excelRequest.getDate();
        List<Questionnaire> questionnaires = excelRepository.findByYearAndMonth(date.toString());
        createRow(questionnaires, sheet);
    }

    public void createXssYear(ExcelDateRequest excelRequest, Sheet sheet){
        createHeader(sheet);
        String date =  excelRequest.getDate();
        List<Questionnaire> questionnaires = excelRepository.findByYear(date.toString());
        createRow(questionnaires, sheet);
    }

    public void createRow(List<Questionnaire> questionnaires, Sheet sheet){
        Long num = 1L;
        for (Questionnaire questionnaire : questionnaires) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            System.out.println(questionnaire.getUserName());
            row.createCell(0).setCellValue(num++);
            row.createCell(1).setCellValue(questionnaire.getUserName());
            row.createCell(2).setCellValue(questionnaire.getSchoolNumber());
            row.createCell(3).setCellValue(questionnaire.getGender());
            row.createCell(4).setCellValue(questionnaire.getDisease());
            row.createCell(5).setCellValue(questionnaire.getContent());
            row.createCell(6).setCellValue(questionnaire.getTime());
        }
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