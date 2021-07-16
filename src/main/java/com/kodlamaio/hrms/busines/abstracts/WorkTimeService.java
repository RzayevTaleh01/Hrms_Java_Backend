package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.entities.concretes.WorkTime;

import java.util.List;

public interface WorkTimeService {
    public DataResult<List<WorkTime>> getAll();
}
