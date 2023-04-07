package com.clinic.dbRepository;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicinePatient {
    private int clientId;
    private int medicineId;
    private LocalDate issueDate;
    private String notes;
}
