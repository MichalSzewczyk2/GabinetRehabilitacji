package com.clinic.dbRepository;

import com.clinic.dbTables.WorkSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkScheduleRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<WorkSchedule> getAll() {
    return jdbcTemplate.query("SELECT * FROM work_schedules", BeanPropertyRowMapper.newInstance(WorkSchedule.class));
  }

  public WorkSchedule getById(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id must be greater than 0");
    }
    return jdbcTemplate.queryForObject("SELECT * FROM work_schedules WHERE schedule_id = ?",
      new Object[]{id}, (rs, rowNum) -> WorkSchedule.getWorkScheduleFromResultSet(rs));
  }

  public void add(WorkSchedule workSchedule) {
    jdbcTemplate.update("INSERT INTO work_schedules (doctor_id, date, start_time, end_time) VALUES (?,?,?,?)",
      workSchedule.getDoctorId(), workSchedule.getDate(), workSchedule.getStartTime(), workSchedule.getEndTime());
  }

  public void update(WorkSchedule workSchedule) {
    jdbcTemplate.update("UPDATE work_schedules SET doctor_id = ?, date = ?, start_time = ?, end_time = ? WHERE schedule_id = ?",
      workSchedule.getDoctorId(), workSchedule.getDate(), workSchedule.getStartTime(), workSchedule.getEndTime(), workSchedule.getScheduleId());
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM work_schedules WHERE schedule_id = ?", id);
  }
}
