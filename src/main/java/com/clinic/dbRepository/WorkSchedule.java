package com.clinic.dbRepository;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkSchedule {
    private int scheduleId;
    private int doctorId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalDate endTime;

}