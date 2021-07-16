package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.SystemPersonal;
import com.kodlamaio.hrms.entities.dto.SystemPersonalUpdateDto;

import java.util.List;

public interface SystemPersonalService {
    public Result create(SystemPersonal systemPersonal);
    public DataResult<List<SystemPersonal>> getAll();
    public Result update(SystemPersonalUpdateDto systemPersonalUpdateDto);
}
