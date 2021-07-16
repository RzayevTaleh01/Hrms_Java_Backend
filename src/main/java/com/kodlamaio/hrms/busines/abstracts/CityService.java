package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.City;

import java.util.List;

public interface CityService {
    Result add(City city);
    public DataResult<List<City>> getAll();
}
