package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.Employer;

public interface ActivationBySystemPersonalService {
    void createActivationByStaff(Employer employer);
    Result activateEmployer(int employerId,int staffId);
}
