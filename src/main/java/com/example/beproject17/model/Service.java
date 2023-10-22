package com.example.beproject17.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Service {
    private Integer id;
    private String serviceName;
    private String description;
    private String image;
    private Boolean isDeleted;
}
