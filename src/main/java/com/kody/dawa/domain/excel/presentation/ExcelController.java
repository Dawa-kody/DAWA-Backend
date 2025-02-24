package com.kody.dawa.domain.excel.presentation;

import com.kody.dawa.domain.excel.presentation.dto.request.ExcelRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/excel")
@RestController
@RequiredArgsConstructor
public class ExcelController {

    @PostMapping("xss")
    public void xssCreate(HttpServletResponse response/*, @RequestBody ExcelRequest request*/) throws IOException {
        String fileName = URLEncoder.encode("test.xlsx", StandardCharsets.UTF_8);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("TEST");
            header.createCell(1).setCellValue("VALUE");

            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue("TEST1");
            row.createCell(1).setCellValue("VALUE1");

            workbook.write(response.getOutputStream());
        }
    }
}
