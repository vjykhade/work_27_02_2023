package com.vjykhade.api.spring.web.repository;

import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyUploadRepository extends JpaRepository<CurrencyUpload, Long> {

}
