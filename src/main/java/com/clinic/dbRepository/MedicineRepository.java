package com.clinic.dbRepository;

import com.clinic.dbTables.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicineRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<Medicine> getAll() {
    return jdbcTemplate.query("SELECT * FROM medicine", BeanPropertyRowMapper.newInstance(Medicine.class));
  }

  public Medicine getById(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id must be greater than 0");
    }
    return jdbcTemplate.queryForObject("SELECT * FROM medicine WHERE medicine_id = ?",
      new Object[]{id}, (rs, rowNum) -> Medicine.getMedicineFromResultSet(rs));
  }

  public void add(Medicine medicine) {
    jdbcTemplate.update("INSERT INTO medicine (name, description, regular_price, prescription_price) VALUES (?,?,?,?)",
      medicine.getName(), medicine.getDescription(), medicine.getRegularPrice(), medicine.getPrescriptionPrice());
  }

  public void update(Medicine medicine) {
    jdbcTemplate.update("UPDATE medicine SET name = ?, description = ?, regular_price = ?, prescription_price = ? WHERE medicine_id = ?",
      medicine.getName(), medicine.getDescription(), medicine.getRegularPrice(), medicine.getPrescriptionPrice(), medicine.getMedicineId());
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM medicine WHERE medicine_id = ?", id);
  }
}
