package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.ActivationCode;
import com.kodlamaio.hrms.entities.concretes.City;
import com.kodlamaio.hrms.entities.concretes.User;

import java.util.List;

public interface ActivationCodeService {
    ActivationCode getByCode(String code);
    String createActivationCode(User user);
    Result activateUser(String code);
    DataResult<List<ActivationCode>> getAll();

}
