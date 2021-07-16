package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.JobFavorites;

import java.util.List;


public interface JobFavoritesService {
    public DataResult<List<JobFavorites>> getByEmployeeId(int employeeId);
    public Result addFavorite(int employeeId, int jobId);
    public Result removeFavorite(int favoriteId);
}
