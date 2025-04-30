package com.kody.dawa.domain.excel.presentation;

import com.kody.dawa.domain.excel.presentation.dto.request.AutoRequest;
import com.kody.dawa.domain.excel.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/excel")
@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;
    private final Logger logger = LoggerFactory.getLogger(ExcelController.class);

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

    private static final String TARGET_COLOR_HEX = "FFFFC000";

    @PostMapping("/change")
    public ResponseEntity<?> change(@RequestParam("file") MultipartFile file) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    CellStyle style = cell.getCellStyle();
                    String cellValue = getCellValueAsString(cell);

                    if (style instanceof XSSFCellStyle xssfCellStyle) {
                        if (xssfCellStyle.getFillPattern() == FillPatternType.SOLID_FOREGROUND) {
                            XSSFColor color = xssfCellStyle.getFillForegroundColorColor();
                            if (color != null && color.getARGBHex() != null) {
                                String argbHex = color.getARGBHex(); // 예: "FFFFC000"
                                logger.info("셀 값: " + cellValue + " | 색상: " + argbHex);

                                // 색상 일치 확인
                                if (TARGET_COLOR_HEX.equalsIgnoreCase(argbHex)) {
                                    Map<String, String> map = new HashMap<>();
                                    map.put(cellValue, "여성");
                                    result.add(map);
                                }
                                else if (!cellValue.isBlank() && !cellValue.equals("전출")){
                                    Map<String, String> map = new HashMap<>();
                                    map.put(cellValue, "남성");
                                    result.add(map);
                                }
                            }
                        }
                    }
                }
            }
            return ResponseEntity.ok(result);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private boolean isMatchingColor(CellStyle style, String targetHex) {
        if (style instanceof XSSFCellStyle xssfCellStyle) {
            if (xssfCellStyle.getFillPattern() == FillPatternType.SOLID_FOREGROUND) {
                XSSFColor color = xssfCellStyle.getFillForegroundColorColor();
                if (color != null && color.getARGBHex() != null) {
                    // 예: "FFFFF2CC" → 끝 6자리만 비교 (F2CC 등)
                    String argb = color.getARGBHex(); // 예: "FFFFF2CC"
                    return argb.toUpperCase().endsWith(targetHex.toUpperCase());
                }
            }
        }
        return false;
    }


    private String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

}