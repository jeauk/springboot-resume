package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.EducationForm;
import com.example.resume.entity.HighSchoolForm;

public interface EducationFormRepository extends JpaRepository <EducationForm, Long>  {
    List<EducationForm> findByUserId(Long userId);
    void deleteByUserId(Long userId);
  
}
