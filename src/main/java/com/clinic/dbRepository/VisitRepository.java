package com.clinic.dbRepository;

import com.clinic.dbTables.Visit;
import com.clinic.helpers.VisitListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<Visit> getAll() {
    return jdbcTemplate.queryForObject("SELECT * FROM visits", new VisitListRowMapper());
  }

  public Visit getById(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id must be greater than 0");
    }
    return jdbcTemplate.queryForObject("SELECT * FROM visits WHERE visit_id = ?", new Object[]{id}, (rs, rowNum) -> Visit.getVisitFromResultSet(rs));
  }

  public void add(Visit visit) {
    jdbcTemplate.update("INSERT INTO visits (status, date, time, progress_assesment, notes, client_id, doctor_id, surgery_id) VALUES (?,?,?,?,?,?,?,?)",
      visit.getStringStatus(), visit.getDate(), visit.getTime(), visit.getProgressAssessment(), visit.getNotes(), visit.getClientId(), visit.getDoctorId(), visit.getSurgeryId());
  }

  public void update(Visit visit) {
    jdbcTemplate.update("UPDATE visits SET status = ?, date = ?, time = ?, progress_assesment = ?, notes = ?, client_id = ?, doctor_id = ?, surgery_id = ? WHERE visit_id = ?",
      visit.getStringStatus(), visit.getDate(), visit.getTime(), visit.getProgressAssessment(), visit.getNotes(), visit.getClientId(), visit.getDoctorId(), visit.getSurgeryId(), visit.getVisitId());
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM visits WHERE visit_id = ?", id);
  }
}
