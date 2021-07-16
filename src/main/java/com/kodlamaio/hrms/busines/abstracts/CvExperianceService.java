package com.kodlamaio.hrms.busines.abstracts;


import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvExperiance;
import com.kodlamaio.hrms.entities.dto.CvExperianceDto;

import java.util.List;

public interface CvExperianceService {
    Result add(CvExperianceDto cvExperianceDto);
    Result delete(int experianceId);
    DataResult<List<CvExperiance>> getByCvId(int id);
}
