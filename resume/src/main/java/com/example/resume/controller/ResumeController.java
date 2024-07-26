package com.example.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/resume")
public class ResumeController {

  @Autowired CertificationFormRepository certificationFormRepository;
  @Autowired EducationFormRepository educationFormRepository;
  @Autowired ExperienceFormRepository experienceFormRepository;
  @Autowired HighSchoolFormRepository highSchoolFormRepository;  
  @Autowired MaxLengthInputRepository maxLengthInputRepository;
  @Autowired UserInfoRepository userInfoRepository;

  @PostMapping("/data")
  public void saveResumeData(@RequestBody ResumeData resumeData) {
    System.out.println("1111");
    User user = new User();
    // user.setId(resumeData.getId()); // 사용자 ID 설정
    user.setId(1L); // 사용자 ID 설정

    // UserInfo 저장
    UserInfo userInfo = resumeData.getUserInfo();
    userInfo.setUser(user);
    userInfoRepository.save(userInfo);

    // HighSchoolForms 저장
    List<HighSchoolForm> hsForms = resumeData.getHighSchoolForm();
    for (HighSchoolForm form : hsForms) {
      form.setUser(user);
      highSchoolFormRepository.save(form);
    }

    // EducationForms 저장
    List<EducationForm> edForms = resumeData.getEducationForm();
    for (EducationForm form : edForms) {
      form.setUser(user);
      educationFormRepository.save(form);
    }

    // ExperienceForms 저장
    List<ExperienceForm> expForms = resumeData.getExperienceForm();
    for (ExperienceForm form : expForms) {
      form.setUser(user);
      experienceFormRepository.save(form);
    }

    // CertificationForms 저장
    List<CertificationForm> certForms = resumeData.getCertificationForm();
    for (CertificationForm form : certForms) {
      form.setUser(user);
      certificationFormRepository.save(form);
    }

    // MaxLengthInputs 저장
    List<MaxLengthInput> maxInputs = resumeData.getMaxLengthInput();
    for (MaxLengthInput input : maxInputs) {
      input.setUser(user);
      maxLengthInputRepository.save(input);
    }
  }

  @GetMapping("/data/{userId}")
  public ResumeData getResumeData(@PathVariable Long userId) {
    ResumeData resumeData = new ResumeData();

    Optional<UserInfo> userInfoOpt = userInfoRepository.findById(userId);
    userInfoOpt.ifPresent(resumeData::setUserInfo);

    resumeData.setHighSchoolForm(highSchoolFormRepository.findByUserId(userId));
    resumeData.setEducationForm(educationFormRepository.findByUserId(userId));
    resumeData.setExperienceForm(experienceFormRepository.findByUserId(userId));
    resumeData.setCertificationForm(certificationFormRepository.findByUserId(userId));
    resumeData.setMaxLengthInput(maxLengthInputRepository.findByUserId(userId));

    return resumeData;
  }
}
