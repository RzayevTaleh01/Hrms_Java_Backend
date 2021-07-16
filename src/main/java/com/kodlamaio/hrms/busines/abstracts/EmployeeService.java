package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.Employee;
import com.kodlamaio.hrms.entities.dto.EmployeeRegisterDto;

import java.util.List;

public interface EmployeeService {
    DataResult<List<Employee>> getAll();
    DataResult<Employee> getByNationalNumber(String nationalNumber);
    DataResult<Employee> getByEmail(String email);
    Result add(EmployeeRegisterDto employeeRegisterDto);
    DataResult<List<Employee>> getMailVerifyTrue();
}
