package com.clinic.dbRepository;

import com.clinic.dbTables.MedicinePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicinePatientRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<MedicinePatient> getAll() {
    return jdbcTemplate.query("SELECT * FROM medicine_patient", BeanPropertyRowMapper.newInstance(MedicinePatient.class));
  }

  public MedicinePatient getById(int medicineId, int patientId) {
    if (medicineId <= 0 || patientId <= 0) {
      throw new IllegalArgumentException("Id must be greater than 0");
    }
    return jdbcTemplate.queryForObject("SELECT * FROM medicine_patient WHERE medicine_id =? AND patient_id = ?",
      new Object[]{medicineId, patientId}, (rs, rowNum) -> MedicinePatient.getMedicinePatientFromResultSet(rs));
  }

  public void add(MedicinePatient medicinePatient) {
    jdbcTemplate.update("INSERT INTO  medicine_patient (medicine_id, patient_id, issue_date, notes) VALUES (?,?,?,?)",
      medicinePatient.getMedicineId(), medicinePatient.getPatientId(), medicinePatient.getIssueDate(), medicinePatient.getNotes());
  }

  public void update(MedicinePatient medicinePatient) {
    jdbcTemplate.update("UPDATE medicine_patient SET issue_date = ?, notes = ? WHERE medicine_id = ? AND patient_id = ?",
      medicinePatient.getIssueDate(), medicinePatient.getNotes(), medicinePatient.getMedicineId(), medicinePatient.getPatientId());
  }

  public void delete(int medicineId, int patientId) {
    jdbcTemplate.update("DELETE  FROM medicine_patient WHERE medicine_id = ? AND patient_id = ?",
      medicineId, patientId);
  }
}
