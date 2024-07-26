package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.CertificationForm;
import com.example.resume.entity.HighSchoolForm;

public interface CertificationFormRepository extends JpaRepository<CertificationForm, Long>{
    List<CertificationForm> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
