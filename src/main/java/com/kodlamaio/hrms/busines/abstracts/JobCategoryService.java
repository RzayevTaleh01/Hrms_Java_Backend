package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.JobCategory;

import java.util.List;

public interface JobCategoryService {
    DataResult<List<JobCategory>> getAll();
    Result add(JobCategory jobCategory);
    DataResult<JobCategory> getByName(String name);
}
