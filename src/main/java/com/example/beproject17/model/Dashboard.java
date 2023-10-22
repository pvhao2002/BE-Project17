package com.example.beproject17.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Dashboard implements Serializable {
    private Integer totalUser;
    private Integer totalAppointment;
    private Integer totalService;
    private Integer totalMedicalRecord;
}
