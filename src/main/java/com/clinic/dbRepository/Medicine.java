package com.clinic.dbRepository;

import lombok.Data;

@Data
public class Medicine {
    private int medicineId;
    private String name;
    private String description;
    private int regularPrice;
    private int prescriptionPrice;
}
