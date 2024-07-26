package com.example.resume.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.entity.HighSchoolForm;
import com.example.resume.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository <UserInfo, Long>{
  
    Optional<UserInfo> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
