package com.kodlamaio.hrms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegisterDto {
    private String email;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String nationalNumber;
    private LocalDate birthDate;
}
