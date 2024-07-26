package com.example.resume.dto;

import java.util.List;

import com.example.resume.entity.CertificationForm;
import com.example.resume.entity.EducationForm;
import com.example.resume.entity.ExperienceForm;
import com.example.resume.entity.HighSchoolForm;
import com.example.resume.entity.MaxLengthInput;
import com.example.resume.entity.UserInfo;

import lombok.Data;

@Data
public class ResumeData {
  Long id;
  List<CertificationForm> certificationForm;
  List<EducationForm> educationForm;
  List<ExperienceForm> experienceForm;
  List<HighSchoolForm> highSchoolForm;
  List<MaxLengthInput> maxLengthInput;
  UserInfo userInfo;
}
