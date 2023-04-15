package com.clinic.dbRepository;

import com.clinic.dbTables.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClinicRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Clinic> getAll(){
        return jdbcTemplate.query("SELECT * FROM clinic", BeanPropertyRowMapper.newInstance(Clinic.class));
    }

    public Clinic getById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return jdbcTemplate.queryForObject("SELECT * FROM clinic WHERE clinic_id = ?",
                new Object[] {id}, (rs, rowNum) -> Clinic.getClinicFromResultSet(rs));
    }

    public void add(Clinic clinic){
        jdbcTemplate.update("INSERT INTO clinic (name, address, phone_number, mail) VALUES (?,?,?,?)",
                clinic.getName(), clinic.getAddress(), clinic.getPhoneNumber(), clinic.getMail());
    }

    public void update(Clinic clinic){
        jdbcTemplate.update("UPDATE clinic SET name = ?, address = ?, phone_number = ?, mail = ? WHERE clinic_id = ?",
                clinic.getName(), clinic.getAddress(), clinic.getPhoneNumber(), clinic.getMail(), clinic.getClinicId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM clinic WHERE clinic_id = ?", id);
    }
}
 