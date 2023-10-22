package com.example.beproject17.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Integer roleId;
    private Boolean isDeleted;
}
