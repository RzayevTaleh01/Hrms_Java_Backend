package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.Employer;
import com.kodlamaio.hrms.entities.concretes.EmployerUpdate;
import com.kodlamaio.hrms.entities.dto.EmployerRegisterDto;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();
    DataResult<Employer> getByEmail(String email);
    Result add(EmployerRegisterDto employerDto);
    DataResult<Employer> getById(int id);
    Result update(EmployerUpdate employerUpdate);
    Result verifyUpdate(int employerUpdateId,int staffId);
}
