package com.kody.dawa.domain.excel.presentation;

import com.kody.dawa.domain.excel.presentation.dto.request.AutoRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelDateRequest;
import com.kody.dawa.domain.excel.presentation.dto.request.ExcelStudentRequest;
import com.kody.dawa.domain.excel.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RequestMapping("/excel")
@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("xss_student")
    public void xssStudent(HttpServletResponse response, @RequestBody ExcelStudentRequest request) throws IOException {
        String fileName = request.getFileName()+".xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            excelService.createXssStudent(request,sheet);

            workbook.write(response.getOutputStream());
        }
    }

    @PostMapping("/xss_month")
    public void xssMonth(HttpServletResponse response, @RequestBody ExcelDateRequest request) throws IOException {
        String fileName = request.getFileName()+".xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            excelService.createXssMonth(request,sheet);
            workbook.write(response.getOutputStream());
        }
    }

    @PostMapping("/xss_year")
    public void xssYear(HttpServletResponse response, @RequestBody ExcelDateRequest request) throws IOException {
        String fileName = request.getFileName()+".xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            excelService.createXssYear(request,sheet);

            workbook.write(response.getOutputStream());
        }
    }

    @PostMapping("/auto")
    public ResponseEntity<?> downloadSavedExcel(@RequestBody AutoRequest request) throws IOException {
        String filePath = "C:/data/auto_save/" + request.getDate() + ".xlsx";
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}