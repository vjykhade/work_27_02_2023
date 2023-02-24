package com.vjykhade.api.spring.web.controller;

import com.vjykhade.api.spring.web.helper.ExcelDownloadHelper;
import com.vjykhade.api.spring.web.service.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelDownloadController {

    @Autowired
    ExcelUploadService excelUploadService;

    @GetMapping("/download-excel")
    public ResponseEntity getExcelDownloadFile()
    {
        String filename = "currency_details.xlsx";
        InputStreamResource file = new InputStreamResource(excelUploadService.loadDataToExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
