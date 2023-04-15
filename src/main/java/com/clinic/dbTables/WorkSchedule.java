package com.clinic.dbTables;

import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkSchedule {
  private int scheduleId;
  private int doctorId;
  private LocalDate date;
  private LocalTime startTime;
  private LocalTime endTime;

  @NotNull
  @SneakyThrows
  public static WorkSchedule getWorkScheduleFromResultSet(ResultSet rs) {
    WorkSchedule workSchedule = new WorkSchedule();
    workSchedule.setScheduleId(rs.getInt("schedule_id"));
    workSchedule.setDoctorId(rs.getInt("doctor_id"));
    workSchedule.setDate(rs.getDate("date").toLocalDate());
    workSchedule.setStartTime(rs.getTime("start_time").toLocalTime());
    workSchedule.setEndTime(rs.getTime("end_time").toLocalTime());
    return workSchedule;
  }

}
