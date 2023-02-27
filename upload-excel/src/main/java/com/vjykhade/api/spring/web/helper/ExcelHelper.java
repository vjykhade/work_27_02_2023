package com.vjykhade.api.spring.web.helper;

import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "Id", "From_Currency", "To_Currency", "Conversion_Multiple" };
    static String SHEET = "currency_details";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CurrencyUpload> excelToDBUpload(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<CurrencyUpload> currencyUploadList = new ArrayList<CurrencyUpload>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header data
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                CurrencyUpload currencyUpload = new CurrencyUpload();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            currencyUpload.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            currencyUpload.setFromCurrency(currentCell.getStringCellValue());
                            break;

                        case 2:
                            currencyUpload.setToCurrency(currentCell.getStringCellValue());
                            break;

                        case 3:
                            currencyUpload.setConversionMultiple(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                currencyUploadList.add(currencyUpload);
            }

            workbook.close();

            return currencyUploadList;
        } catch (IOException e) {
            throw new RuntimeException("Fail to get data from Excel File: " + e.getMessage());
        }
    }
}

