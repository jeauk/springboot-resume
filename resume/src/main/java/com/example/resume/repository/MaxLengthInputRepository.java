package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.HighSchoolForm;
import com.example.resume.entity.MaxLengthInput;

public interface MaxLengthInputRepository extends JpaRepository <MaxLengthInput, Long>{
  
    List<MaxLengthInput> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
