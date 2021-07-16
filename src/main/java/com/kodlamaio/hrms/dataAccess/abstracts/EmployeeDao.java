package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {
    Employee findByNationalNumber(String nationalNumber);
    Employee findByEmail(String email);
    List<Employee> findByMailVerifyTrue();
}
