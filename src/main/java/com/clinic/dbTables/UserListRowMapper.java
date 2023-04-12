package com.clinic.dbTables;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListRowMapper implements RowMapper<List<User>> {
    @Override
    public List<User> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<User> users = new ArrayList<>();
        User firstUser = User.getUserFromResultSet(rs);
        users.add(firstUser);
        while (rs.next()) {
            User newUser = User.getUserFromResultSet(rs);
            users.add(newUser);
        }
        return users;
    }
}
