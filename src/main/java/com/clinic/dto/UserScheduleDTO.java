package com.clinic.dto;

public class UserScheduleDTO {

  private int userId;
  private int scheduleId;

  public UserScheduleDTO(int userId, int scheduleId) {
    this.userId = userId;
    this.scheduleId = scheduleId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(int scheduleId) {
    this.scheduleId = scheduleId;
  }
}
