package com.example.beproject17.model;


import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Appointment {
    private Integer id;
    private Integer userId;
    private String fullName;
    private String phone;
    private String email;
    private Integer serviceId;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date appointmentDate;
    private String description;
    private String status;

    private User user;
    private Service service;
}
