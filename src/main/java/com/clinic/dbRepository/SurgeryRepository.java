package com.clinic.dbRepository;

import com.clinic.dbTables.Surgery;
import com.clinic.helpers.SurgeryListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurgeryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Surgery> getAll(){
        return jdbcTemplate.queryForObject("SELECT * FROM surgeries", new SurgeryListRowMapper());
    }

    public Surgery getById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return jdbcTemplate.queryForObject("SELECT * FROM surgeries WHERE surgery_id = ?",
                new Object[] {id}, (rs, rowNum) -> Surgery.getSurgeryFromResultSet(rs));
    }

    public void add(Surgery surgery){
        jdbcTemplate.update("INSERT INTO surgeries (name, authorization_needed, regular_price, prescription_price, description, duration) VALUES  (?,?,?,?,?,?)",
                surgery.getName(), surgery.getAuthorizationNeeded(), surgery.getRegularPrice(), surgery.getPrescriptionPrice(), surgery.getDescription(), surgery.getDuration());
    }

    public void update(Surgery surgery){
        jdbcTemplate.update("UPDATE surgeries SET name = ?, authorization_needed = ?, regular_price = ?, prescription_price = ?, description = ?, duration =? WHERE  surgery_id = ?",
                surgery.getName(), surgery.getAuthorizationNeeded(), surgery.getRegularPrice(), surgery.getPrescriptionPrice(), surgery.getDescription(), surgery.getDuration(), surgery.getSurgeryId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM surgeries WHERE surgery_id = ?", id);
    }
}
