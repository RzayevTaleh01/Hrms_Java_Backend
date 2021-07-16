package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvSchool;
import com.kodlamaio.hrms.entities.dto.CvSchoolDto;

import java.util.List;

public interface CvSchoolService {
    public Result addSchool(CvSchoolDto cvSchoolDto);
    public Result deleteSchool(int schoolId);
    public DataResult<List<CvSchool>> getByCvId(int cvId);
}
