package com.clinic.dbTables;

import lombok.Data;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDate;

@Data
public class MedicinePatient {
    private int medicineId;
    private int patientId;
    private LocalDate issueDate;
    private String notes;

    @SneakyThrows
    public static MedicinePatient getMedicinePatientFromResultSet(ResultSet rs){
        MedicinePatient medicinePatient = new MedicinePatient();
        medicinePatient.setMedicineId(rs.getInt("medicine_id"));
        medicinePatient.setPatientId(rs.getInt("patient_id"));
        medicinePatient.setIssueDate(rs.getDate("issue_date").toLocalDate());
        medicinePatient.setNotes(rs.getString("notes"));
        return medicinePatient;
    }
}
