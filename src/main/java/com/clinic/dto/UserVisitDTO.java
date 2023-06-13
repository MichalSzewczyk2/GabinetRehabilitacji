package com.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserVisitDTO {

  private String visitName;
  private LocalDate date;
  private int doctorId;
  private LocalTime start;

  public String getVisitName() {
    return visitName;
  }

  public void setVisitName(String visitName) {
    this.visitName = visitName;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }

  public LocalTime getStart() {
    return start;
  }

  public void setStart(LocalTime start) {
    this.start = start;
  }
}
