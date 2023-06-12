package com.clinic.dto;

import java.time.LocalDate;

public class UserVisitDTO {

  private String visitName;
  private LocalDate date;

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
}
