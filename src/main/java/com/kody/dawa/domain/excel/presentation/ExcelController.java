package com.kody.dawa.domain.excel.presentation;

import com.kody.dawa.domain.excel.presentation.dto.request.AutoRequest;
import com.kody.dawa.domain.excel.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/excel")
@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

//    @PostMapping("xss_student")
//    public void xssStudent(HttpServletResponse response, @RequestBody ExcelStudentRequest request) throws IOException {
//        String fileName = request.getFileName()+".xlsx";
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//
//        try (Workbook workbook = new XSSFWorkbook()) {
//            Sheet sheet = workbook.createSheet();
//            excelService.createXssStudent(request,sheet);
//
//            workbook.write(response.getOutputStream());
//        }
//    }
//
//    @PostMapping("/xss_month")
//    public void xssMonth(HttpServletResponse response, @RequestBody ExcelDateRequest request) throws IOException {
//        String fileName = request.getFileName()+".xlsx";
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//
//        try (Workbook workbook = new XSSFWorkbook()) {
//            Sheet sheet = workbook.createSheet();
//            excelService.createXssMonth(request,sheet);
//            workbook.write(response.getOutputStream());
//        }
//    }
//
//    @PostMapping("/xss_year")
//    public void xssYear(HttpServletResponse response, @RequestBody ExcelDateRequest request) throws IOException {
//        String fileName = request.getFileName()+".xlsx";
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//
//        try (Workbook workbook = new XSSFWorkbook()) {
//            Sheet sheet = workbook.createSheet();
//            excelService.createXssYear(request,sheet);
//
//            workbook.write(response.getOutputStream());
//        }
//    }

    @GetMapping
    public void downloadExcel(HttpServletResponse response) throws IOException {
        String fileName = "backup.xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()){
            excelService.createXss(workbook);
            workbook.write(response.getOutputStream());
        }
    }

    //유저 role 확인해서 엑셀가능 시키기
    @PostMapping
    public void downloadSavedExcel(HttpServletResponse response, @RequestBody AutoRequest request, @RequestHeader(value = "authorization", required = false) String authorization) throws IOException {
//        String filePath = "C:/data/auto_save/" + request.getDate() + ".xlsx";
//        File file = new File(filePath);
//
//        if (!file.exists()) {
//            return ResponseEntity.badRequest().body("그날애 관련된 값이 없습니다.");
//        }

//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);

        String fileName = request.getDate()+".xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()){
            excelService.createDate(workbook,request.getDate());
            workbook.write(response.getOutputStream());
        }

    }
}