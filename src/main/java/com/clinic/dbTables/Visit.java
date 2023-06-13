package com.clinic.dbTables;

import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Visit {
  private int visitId;
  private VisitStatus status;
  private LocalDate date;
  private LocalTime time;
  private String progressAssessment;
  private String notes;
  private int clientId;
  private int doctorId;
  private int surgeryId;


  public String getStringStatus(){
    return switch (this.status){
      case PENDING -> "PENDING";
      case ENDED -> "ENDED";
      case CANCELED -> "CANCELED";
    };
  }

  @NotNull
  @SneakyThrows
  public static Visit getVisitFromResultSet(ResultSet rs) {
    Visit visit = new Visit();
    visit.setVisitId(rs.getInt("visit_id"));
    visit.setStatus(VisitStatus.setVisitStatus(rs.getString("status")));
    visit.setDate(rs.getDate("date").toLocalDate());
    visit.setTime(rs.getTime("time").toLocalTime());
    visit.setProgressAssessment(rs.getString("progress_assesment"));
    visit.setNotes(rs.getString("notes"));
    visit.setClientId(rs.getInt("client_id"));
    visit.setDoctorId(rs.getInt("doctor_id"));
    visit.setSurgeryId(rs.getInt("surgery_id"));
    return visit;
  }

}
