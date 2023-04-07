package com.clinic.dbTables;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Visit {
    private int visitId;
    private VisitStatus status;
    private LocalDate date;
    private LocalTime time;
    private String progressAssessment;
    private String notes;
    private int clientId;
    private int doctorId;
    private int surgeryId;

}
