package com.clinic.dbRepository;

import com.clinic.dbTables.User;
import com.clinic.dbTables.UserListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<User> getAll(){
        return jdbcTemplate.queryForObject("SELECT * FROM users", new UserListRowMapper());
    }
    public void addUser(User user){
        jdbcTemplate.update("INSERT INTO users (name, surname, address, birth_date, phone_number, mail, user_type, login, password) VALUES (?,?,?,?,?,?,?,?,?)",
                user.getName(), user.getSurname(), user.getAddress(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail(), user.getUserType(), user.getUsername(), user.getPassword());
    }

    public User getUserById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?",
                new Object[] { id }, (rs, rowNum) -> User.getUserFromResultSet(rs)
        );
    }
}
