package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.HighSchoolForm;
import com.example.resume.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository <UserInfo, Long>{
  
    List<UserInfo> findByUserId(Long userId);
}
