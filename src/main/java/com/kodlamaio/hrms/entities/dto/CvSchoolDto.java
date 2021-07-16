package com.kodlamaio.hrms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvSchoolDto {
    private int CvId;
    private String name;
    private String department;
    private LocalDate startDate;
    private LocalDate endDate;
}
