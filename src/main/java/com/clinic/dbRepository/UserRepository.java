package com.clinic.dbRepository;

import com.clinic.dbTables.User;
import com.clinic.dbTables.UserType;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<User> getAll(){
        return jdbcTemplate.query("SELECT * FROM users",
                BeanPropertyRowMapper.newInstance(User.class));
    }
    public void addUser(User user){
        jdbcTemplate.update("INSERT INTO users (name, surname, address, birth_date, phone_number, mail, user_type, login, password) VALUES (?,?,?,?,?,?,?,?,?)",
                user.getName(), user.getSurname(), user.getAddress(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail(), user.getUserType(), user.getUsername(), user.getPassword());
    }

    public User getUserById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?",
                new Object[] { id }, (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setAddress(rs.getString("address"));
                    user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    user.setPhoneNumber(rs.getInt("phone_number"));
                    user.setEmail(rs.getString("mail"));
                    user.setUserType(UserType.setUserType(rs.getString("user_type")));
                    user.setUsername(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    return user;}
        );
    }
}
