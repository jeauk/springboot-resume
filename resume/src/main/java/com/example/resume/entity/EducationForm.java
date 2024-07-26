package com.example.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class EducationForm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String school;
  String degree;
  String startDate;
  String endDate;
  String graduationStatus;
  @ManyToOne
  User user;
}
