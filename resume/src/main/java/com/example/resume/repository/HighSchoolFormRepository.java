package com.example.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.HighSchoolForm;

public interface HighSchoolFormRepository extends JpaRepository <HighSchoolForm, Long>{
  
}
