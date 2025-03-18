package com.kody.dawa.domain.excel.service.impl;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelDateRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelStudentRequest;
import com.kody.dawa.domain.excel.repository.ExcelRepository;
import com.kody.dawa.domain.excel.service.ExcelService;
import com.kody.dawa.domain.questionnaire.entity.Questionnaire;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void autoSave(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd");
        String date = now.format(dateTimeFormatter);

        List<Questionnaire> questionnaires = excelRepository.findByFormattedDate(date);
        saveToExcel(questionnaires,date);
    }

    private void saveToExcel(List<Questionnaire> questionnaires, String date) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Questionnaires");

        createHeader(sheet);
        createRow(questionnaires, sheet);

        String directoryPath = "C:/data/auto_save/";
        String filePath = directoryPath + date + ".xlsx";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("📂 디렉토리 생성 완료: " + directoryPath);
            } else {
                System.err.println("❌ 디렉토리 생성 실패!");
                return;
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("✅ Excel 파일 저장 완료: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Excel 저장 중 오류 발생: " + e.getMessage());
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createRow(List<Questionnaire> questionnaires, Sheet sheet){
        Long num = 1L;
        for (Questionnaire questionnaire : questionnaires) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
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