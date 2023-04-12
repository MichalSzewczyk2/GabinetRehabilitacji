package com.clinic.dbTables;

public enum UserType {
    CLIENT("client"),
    ADMIN("admin"),
    SECRETARY("secretary"),
    MASSEUR("masseur"),
    PHYSIOTHERAPIST("physiotherapist"),;


    UserType(String client) {
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
}
