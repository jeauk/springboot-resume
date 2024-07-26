package com.example.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ExperienceForm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  Long id;
  String company;
  String position;
  String role;
  String startDate;
  String endDate;
  String employmentStatus;
  String description;
  @ManyToOne
  User user;

    
}
