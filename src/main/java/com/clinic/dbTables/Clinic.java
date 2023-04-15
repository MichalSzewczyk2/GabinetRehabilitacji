package com.clinic.dbTables;

import lombok.Data;
import lombok.SneakyThrows;

import java.sql.ResultSet;

@Data
public class Clinic {
    private int clinicId;
    private String name;
    private String address;
    private int phoneNumber;
    private String mail;

    @SneakyThrows
    public static Clinic getClinicFromResultSet(ResultSet rs) {
        Clinic clinic = new Clinic();
        clinic.setClinicId(rs.getInt("clinic_id"));
        clinic.setName(rs.getString("name"));
        clinic.setAddress(rs.getString("address"));
        clinic.setPhoneNumber(rs.getInt("phone_number"));
        clinic.setMail(rs.getString("mail"));
        return clinic;
    }
}
