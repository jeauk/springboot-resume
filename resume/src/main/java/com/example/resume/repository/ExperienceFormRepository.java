package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.ExperienceForm;
import com.example.resume.entity.HighSchoolForm;

public interface ExperienceFormRepository extends JpaRepository <ExperienceForm, Long>{
    List<ExperienceForm> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
