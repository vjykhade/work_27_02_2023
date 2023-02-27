package com.vjykhade.api.spring.web.service;

import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import com.vjykhade.api.spring.web.helper.ExcelDownloadHelper;
import com.vjykhade.api.spring.web.helper.ExcelHelper;
import com.vjykhade.api.spring.web.repository.CurrencyUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ExcelUploadService     {

    @Autowired
    CurrencyUploadRepository currencyUploadRepository;
    public void saveDataInDB(MultipartFile file) {
        try {
            List<CurrencyUpload> currencyUploadList = ExcelHelper.excelToDBUpload(file.getInputStream());
            currencyUploadRepository.saveAll(currencyUploadList);
        } catch (IOException e) {
            throw new RuntimeException("Fail to upload data into the db: " + e.getMessage());
        }
    }

    public List<CurrencyUpload> getAllCurrencyDetails() {
        return currencyUploadRepository.findAll();
    }

    public Optional<CurrencyUpload> getOneCurrencyDetails(Long id)
    {
        return currencyUploadRepository.findById(id);
    }

    public ByteArrayInputStream loadDataToExcel()
    {
        List<CurrencyUpload> currencyUploadList = currencyUploadRepository.findAll();
        ByteArrayInputStream getByteStreamIn = ExcelDownloadHelper.currencyDetailsToExcel(currencyUploadList);
        return getByteStreamIn;
    }
}
