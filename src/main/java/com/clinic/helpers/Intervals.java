package com.clinic.helpers;

import java.time.LocalTime;

public class Intervals {
  private LocalTime start;
  private LocalTime end;
  private String status;

  public Intervals(LocalTime start, LocalTime end, String status) {
    this.start = start;
    this.end = end;
    this.status = status;
  }

  public LocalTime getStart() {
    return start;
  }

  public void setStart(LocalTime start) {
    this.start = start;
  }

  public LocalTime getEnd() {
    return end;
  }

  public void setEnd(LocalTime end) {
    this.end = end;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
