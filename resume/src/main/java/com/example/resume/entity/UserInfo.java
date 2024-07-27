package com.example.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class UserInfo {
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  Long id;
  String name;
  String phone;
  String address;
  String detailedAddress;
  String email;
  String profileImagePath;

  @ManyToOne
  User user;
}
