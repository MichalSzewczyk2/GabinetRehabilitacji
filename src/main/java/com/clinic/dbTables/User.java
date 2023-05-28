package com.clinic.dbTables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


  public String getStringType(){
    return switch (this.userType) {
      case CLIENT -> "client";
      case ADMIN -> "admin";
      case SECRETARY -> "secretary";
      case MASSEUR -> "masseur";
      case PHYSIOTHERAPIST -> "physiotherapist";
    };
  }

  public void setUserType(UserType userType){
    this.userType = userType;
  }
  public void setUserType(String type){
      userType = UserType.setUserType(type);
  }

  public List<String> getRoles(){
    List<String> list = new ArrayList<>();
    list.add(getStringType());
    return list;
  }

  public boolean equalsUser(User user){
    if(this.id == user.getId()){
      if(this.name.equals(user.getName())){
        if(this.surname.equals(user.getSurname())){
          if(this.address.equals(user.getAddress())) {
            if (this.birthDate.equals(user.getBirthDate())) {
              if (this.phoneNumber == user.getPhoneNumber()) {
                if (this.email.equals(user.getEmail())) {
                  if (this.userType == user.getUserType()) {
                    if (this.username.equals(user.getUsername())) {
                      if (this.password.equals(user.getPassword())) {
                        return true;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }

  @NotNull
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
