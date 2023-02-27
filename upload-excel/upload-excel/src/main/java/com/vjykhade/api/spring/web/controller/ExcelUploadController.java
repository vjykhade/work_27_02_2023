package com.vjykhade.api.spring.web.controller;

import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import com.vjykhade.api.spring.web.helper.ExcelHelper;
import com.vjykhade.api.spring.web.service.ExcelUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class ExcelUploadController {

    @Autowired
    ExcelUploadService excelUploadService;

    @GetMapping("/currency-details")
    public ResponseEntity<List<CurrencyUpload>> getCurrencyDetails()
    {
        try
        {
            List<CurrencyUpload> currencyUploadList = excelUploadService.getAllCurrencyDetails();

            if (currencyUploadList.isEmpty())
            {
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(currencyUploadList,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload-excel")
    public ResponseEntity<List<CurrencyUpload>> uploadExcelFile(@RequestParam("file") MultipartFile multipartFile)
    {
        if (ExcelHelper.hasExcelFormat(multipartFile))
        {
            try
            {
                excelUploadService.saveDataInDB(multipartFile);
            }
            catch (Exception e)
            {
                System.out.println("Error while uploading file: " + e);
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
        List<CurrencyUpload> currencyUploadList = excelUploadService.getAllCurrencyDetails();
        return new ResponseEntity<>(currencyUploadList,HttpStatus.OK);
    }

    @GetMapping("/currency-details/{id}")
    public ResponseEntity<Optional<CurrencyUpload>> getCurrencyDetailsById(@PathVariable Long id)
    {
        if (excelUploadService.getOneCurrencyDetails(id).isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(excelUploadService.getOneCurrencyDetails(id),HttpStatus.OK);
        }

    }
}
