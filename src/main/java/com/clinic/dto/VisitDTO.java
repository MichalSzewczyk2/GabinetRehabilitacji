package com.clinic.dto;

import java.time.LocalDate;

public class VisitDTO {


  private int id;

  private String client;

  private String doctor;

  private LocalDate date;

  private String surgery;

  private String status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public String getDoctor() {
    return doctor;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getSurgery() {
    return surgery;
  }

  public void setSurgery(String surgery) {
    this.surgery = surgery;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
