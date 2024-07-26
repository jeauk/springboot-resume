package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.HighSchoolForm;

public interface HighSchoolFormRepository extends JpaRepository <HighSchoolForm, Long>{
    List<HighSchoolForm> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
