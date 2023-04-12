package com.clinic.dbTables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private String surname;
    private String address;
    private LocalDate birthDate;
    private int phoneNumber;
    private String email;
    private UserType userType;
    private String username;
    private String password;

    @SneakyThrows
    public static User getUserFromResultSet(ResultSet rs) {

        User newUser = new User();
        newUser.setId(rs.getInt("user_id"));
        newUser.setName(rs.getString("name"));
        newUser.setSurname(rs.getString("surname"));
        newUser.setAddress(rs.getString("address"));
        newUser.setBirthDate(rs.getDate("birth_date").toLocalDate());
        newUser.setPhoneNumber(rs.getInt("phone_number"));
        newUser.setEmail(rs.getString("mail"));
        newUser.setUserType(UserType.setUserType(rs.getString("user_type")));
        newUser.setUsername(rs.getString("login"));
        newUser.setPassword(rs.getString("password"));
        return newUser;
    }

}
