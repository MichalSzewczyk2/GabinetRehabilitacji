package com.clinic.helpers;

import com.clinic.annotations.PasswordMatches;
import com.clinic.annotations.ValidEmail;
import com.clinic.annotations.ValidPhoneNumber;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@PasswordMatches
public class UserDTO {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String password;
  private String matchingPassword;

  @ValidEmail
  @NotNull
  private String email;

  @NotNull
  private String address;

  @NotNull
  private LocalDate birthDate;

  @NotNull
  @ValidPhoneNumber
  private String phoneNumber;

  @NotNull
  private String username;


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
