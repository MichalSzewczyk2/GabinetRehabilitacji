package com.clinic.dto;

import org.jetbrains.annotations.NotNull;

public class NameSurnameDTO {

  @NotNull
  private String name;
  @NotNull
  private String surname;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }
}
