package com.example.resume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.resume.dto.ResumeData;
import com.example.resume.entity.*;
import com.example.resume.repository.*;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@Transactional
@RequestMapping("/resume")
public class ResumeController {

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    CertificationFormRepository certificationFormRepository;
    @Autowired
    EducationFormRepository educationFormRepository;
    @Autowired
    ExperienceFormRepository experienceFormRepository;
    @Autowired
    HighSchoolFormRepository highSchoolFormRepository;
    @Autowired
    MaxLengthInputRepository maxLengthInputRepository;
    @Autowired
    UserInfoRepository userInfoRepository;

    @PostMapping("/data")
    public void saveResumeData(@RequestBody ResumeData resumeData) {
        try {
            logger.info("Received resume data: {}", resumeData);

            Long userId = Long.parseLong(resumeData.getId());  // 사용자 ID를 받음
            User user = new User();
            user.setId(userId); // 사용자 ID 설정

            // 해당 사용자의 기존 데이터 삭제
            deleteUserExistingData(user.getId());

            // 새로운 UserInfo 저장
            UserInfo userInfo = resumeData.getUserInfo();
            userInfo.setUser(user);
            userInfoRepository.save(userInfo);

            // 새로운 HighSchoolForms 저장
            List<HighSchoolForm> hsForms = resumeData.getHighSchoolForm();
            for (HighSchoolForm form : hsForms) {
                form.setUser(user);
                highSchoolFormRepository.save(form);
            }

            // 새로운 EducationForms 저장
            List<EducationForm> edForms = resumeData.getEducationForm();
            for (EducationForm form : edForms) {
                form.setUser(user);
                educationFormRepository.save(form);
            }

            // 새로운 ExperienceForms 저장
            List<ExperienceForm> expForms = resumeData.getExperienceForm();
            for (ExperienceForm form : expForms) {
                form.setUser(user);
                experienceFormRepository.save(form);
            }

            // 새로운 CertificationForms 저장
            List<CertificationForm> certForms = resumeData.getCertificationForm();
            for (CertificationForm form : certForms) {
                form.setUser(user);
                certificationFormRepository.save(form);
            }

            // 새로운 MaxLengthInputs 저장
            List<MaxLengthInput> maxInputs = resumeData.getMaxLengthInput();
            for (MaxLengthInput input : maxInputs) {
                input.setUser(user);
                maxLengthInputRepository.save(input);
            }

            logger.info("Resume data saved successfully");

        } catch (Exception e) {
            logger.error("Error saving resume data", e);
            throw e;
        }
    }

    @GetMapping("/data/{userId}")
    public ResumeData getResumeData(@PathVariable Long userId) {
        ResumeData resumeData = new ResumeData();

        Optional<UserInfo> userInfoOpt = userInfoRepository.findByUserId(userId);
        userInfoOpt.ifPresent(resumeData::setUserInfo);

        resumeData.setHighSchoolForm(highSchoolFormRepository.findByUserId(userId));
        resumeData.setEducationForm(educationFormRepository.findByUserId(userId));
        resumeData.setExperienceForm(experienceFormRepository.findByUserId(userId));
        resumeData.setCertificationForm(certificationFormRepository.findByUserId(userId));
        resumeData.setMaxLengthInput(maxLengthInputRepository.findByUserId(userId));

        return resumeData;
    }

    private void deleteUserExistingData(Long userId) {
        userInfoRepository.deleteByUserId(userId);
        highSchoolFormRepository.deleteByUserId(userId);
        educationFormRepository.deleteByUserId(userId);
        experienceFormRepository.deleteByUserId(userId);
        certificationFormRepository.deleteByUserId(userId);
        maxLengthInputRepository.deleteByUserId(userId);
    }
}
