package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.NationalValidationService;
import com.kodlamaio.hrms.entities.concretes.Employee;
import org.springframework.stereotype.Service;

@Service
public class MernisManager implements NationalValidationService {


    @Override
    public boolean validate(Employee employee) {
        if(employee.getNationalNumber().length()!=11){
            return false;
        }
        return true;
    }
}
