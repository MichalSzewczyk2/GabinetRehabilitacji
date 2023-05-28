package com.clinic.dto;

import org.jetbrains.annotations.NotNull;

public class StringDTO {

  @NotNull
  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
