package com.clinic.dbTables;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
  CLIENT("client"),
  ADMIN("admin"),
  SECRETARY("secretary"),
  MASSEUR("masseur"),
  PHYSIOTHERAPIST("physiotherapist");

  UserType(String role) {
  }

  public static UserType setUserType(String type) {

    return switch (type) {
      case "client" -> UserType.CLIENT;
      case "admin" -> UserType.ADMIN;
      case "secretary" -> UserType.SECRETARY;
      case "masseur" -> UserType.MASSEUR;
      case "physiotherapist" -> UserType.PHYSIOTHERAPIST;
      default -> null;
    };
  }

  public static List<String> getList(){
    List<String> list = new ArrayList<>();
    list.add("client");
    list.add("admin");
    list.add("secretary");
    list.add("masseur");
    list.add("physiotherapist");
    return list;
  }
}
