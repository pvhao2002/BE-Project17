package com.example.beproject17.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicalRecord {
    private Integer id;
    private Integer userId;
    private Integer appointmentId;
    private String diagnosis;

    private User user;
    private Appointment appointment;
}
