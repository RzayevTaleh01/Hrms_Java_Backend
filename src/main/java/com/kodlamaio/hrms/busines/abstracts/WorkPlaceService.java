package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.entities.concretes.WorkPlace;

import java.util.List;

public interface WorkPlaceService {
    public DataResult<List<WorkPlace>> getAll();
}
