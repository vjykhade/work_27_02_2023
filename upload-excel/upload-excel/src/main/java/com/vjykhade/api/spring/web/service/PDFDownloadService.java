package com.vjykhade.api.spring.web.service;

import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import com.vjykhade.api.spring.web.repository.CurrencyUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDFDownloadService {

    @Autowired
    CurrencyUploadRepository currencyUploadRepository;

    public List<CurrencyUpload> getCurrencyList()
    {
        return currencyUploadRepository.findAll();
    }
}
