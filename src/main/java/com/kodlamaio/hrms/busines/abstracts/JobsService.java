package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.Jobs;
import com.kodlamaio.hrms.entities.dto.JobsDto;
import com.kodlamaio.hrms.entities.dto.JobsFilter;

import java.util.List;

public interface JobsService {
    Result create(JobsDto jobsDto);
    Result setPasssive(int jobAdId);
    Result setActiveAndConfirm(int jobAdId,int staffId);
    DataResult<List<Jobs>> getAll();
    DataResult<Jobs> getByJobId(int id);
    DataResult<List<Jobs>> getActiveJobs();
    DataResult<List<Jobs>> getActiveAndOrderLastDate();
    DataResult<List<Jobs>> getActiveAndCompanyId(int id);
    DataResult<List<Jobs>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobsFilter jobsFilter);
}
