package com.kodlamaio.hrms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemPersonalUpdateDto {

    private int staffId;

    private String firstName;

    private String lastName;

    private String email;
}
