package com.example.resume.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.resume.dto.ResumeData;
import com.example.resume.entity.CertificationForm;
import com.example.resume.entity.EducationForm;
import com.example.resume.entity.ExperienceForm;
import com.example.resume.entity.HighSchoolForm;
import com.example.resume.entity.MaxLengthInput;
import com.example.resume.entity.User;
import com.example.resume.entity.UserInfo;
import com.example.resume.repository.CertificationFormRepository;
import com.example.resume.repository.EducationFormRepository;
import com.example.resume.repository.ExperienceFormRepository;
import com.example.resume.repository.HighSchoolFormRepository;
import com.example.resume.repository.MaxLengthInputRepository;
import com.example.resume.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CrossOrigin
@RestController
public class ResumeController {

  @Autowired CertificationFormRepository certificationFormRepository;
  @Autowired EducationFormRepository educationFormRepository;
  @Autowired ExperienceFormRepository experienceFormRepository;
  @Autowired HighSchoolFormRepository highSchoolFormRepository;
  @Autowired MaxLengthInputRepository maxLengthInputRepository;
  @Autowired UserInfoRepository userInfoRepository;

  @PostMapping("/resume/data")
  public void saveResumeData(@RequestBody ResumeData resumeData) {
    User user = new User();
    user.setId(resumeData.getId());

    System.out.println(resumeData);

    // Save UserInfo
    UserInfo userInfo = resumeData.getUserInfo();
    userInfo.setUser(user);
    userInfoRepository.save(userInfo);

    // Save HighSchoolForms
    List<HighSchoolForm> hsForms = resumeData.getHighSchoolForm();
    for (HighSchoolForm form : hsForms) {
      form.setUser(user);
      highSchoolFormRepository.save(form);
    }

    // Save EducationForms
    
    List<EducationForm> edForms = resumeData.getEducationForm();
    for (EducationForm form : edForms) {
      form.setUser(user);
      educationFormRepository.save(form);
    }

    // Save ExperienceForms
    List<ExperienceForm> expForms = resumeData.getExperienceForm();
    for (ExperienceForm form : expForms) {
      form.setUser(user);
      experienceFormRepository.save(form);
    }

    // Save CertificationForms
    List<CertificationForm> certForms = resumeData.getCertificationForm();
    for (CertificationForm form : certForms) {
      form.setUser(user);
      certificationFormRepository.save(form);
    }

    // Save MaxLengthInputs
    List<MaxLengthInput> maxInputs = resumeData.getMaxLengthInput();
    for (MaxLengthInput input : maxInputs) {
      input.setUser(user);
      maxLengthInputRepository.save(input);
    }
  }
}
