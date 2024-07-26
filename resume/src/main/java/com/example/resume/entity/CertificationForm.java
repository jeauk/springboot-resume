package com.example.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CertificationForm {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
  Long id;
  String name;
  String issuingOrganization;
  String issueDate;
  @ManyToOne
  User user;
}
