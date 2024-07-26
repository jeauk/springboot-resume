package com.example.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class MaxLengthInput {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  Long id;
  String title;
  String text;
  String maxChars;
  @ManyToOne
  User user;
}
