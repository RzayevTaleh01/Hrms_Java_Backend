package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.entities.concretes.Employee;

public interface NationalValidationService {
    boolean validate(Employee employee);
}
