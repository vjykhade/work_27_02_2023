package com.vjykhade.api.spring.web.helper;

import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.io.IOException;
import java.util.List;

public class ExcelDownloadHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = {"Id", "From_Currency", "To_Currency", "Conversion_Multiple"};
    static String SHEET = "currency_details";

    public static ByteArrayInputStream currencyDetailsToExcel(List<CurrencyUpload> currencyUploadList) {

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
            for (CurrencyUpload currencyUpload : currencyUploadList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(currencyUpload.getId());
                row.createCell(1).setCellValue(currencyUpload.getFromCurrency());
                row.createCell(2).setCellValue(currencyUpload.getToCurrency());
                row.createCell(3).setCellValue(currencyUpload.getConversionMultiple().doubleValue());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to download data from db to excel: " + e.getMessage());
        }

    }
}
