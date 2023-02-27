package com.vjykhade.api.spring.web.controller;

import com.lowagie.text.DocumentException;
import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import com.vjykhade.api.spring.web.helper.PDFGeneratorHelper;
import com.vjykhade.api.spring.web.service.PDFDownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.dialect.SimpleDatabaseVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@RestController
public class PDFDownloadController {

    @Autowired
    PDFDownloadService pdfDownloadService;

        @GetMapping("/download-pdf")
    public void generatePDFFromDB(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String currentDateTime = simpleDateFormat.format(Date.from(Instant.now()));
        String headerkey = "Content-Disposition";
        String headerValue = "attachment; filename=currency_details_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headerValue);
        List<CurrencyUpload> currencyUploadList = pdfDownloadService.getCurrencyList();
        PDFGeneratorHelper generator = new PDFGeneratorHelper();
        generator.GeneratePDF(currencyUploadList, response);

    }
}
