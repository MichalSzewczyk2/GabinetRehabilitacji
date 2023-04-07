package com.clinic.dbTables;

public enum UserType {
    CLIENT,
    ADMIN,
    SECRETARY,
    MASSEUR,
    PHYSIOTHERAPIST;


    public static UserType setUserType(String type) {

        switch (type) {
            case "client":
                return UserType.CLIENT;
            case "admin":
                return UserType.ADMIN;
            case "secretary":
                return UserType.SECRETARY;
            case "masseur":
                return UserType.MASSEUR;
            case "physiotherapist":
                return UserType.PHYSIOTHERAPIST;
            default:
                return null;
        }

    }
}
