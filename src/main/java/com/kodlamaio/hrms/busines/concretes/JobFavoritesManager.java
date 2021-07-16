package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.JobFavoritesService;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobsDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobFavoritesDao;
import com.kodlamaio.hrms.entities.concretes.JobFavorites;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobFavoritesManager implements JobFavoritesService {

    private JobFavoritesDao jobFavoritesDao;
    private EmployeeDao employeeDao;
    private JobsDao jobsDao;

    @Autowired
    public JobFavoritesManager(JobFavoritesDao jobFavoritesDao, EmployeeDao employeeDao, JobsDao jobsDao) {
        this.jobFavoritesDao = jobFavoritesDao;
        this.employeeDao = employeeDao;
        this.jobsDao = jobsDao;
    }

    @Override
    public DataResult<List<JobFavorites>> getByEmployeeId(int employeeId) {
        if(!this.employeeDao.existsById(employeeId)){
            return new ErrorDataResult<List<JobFavorites>>("Belə bir istifadəçi mövcud deyil!");
        }
        return new SuccessDataResult<List<JobFavorites>>(this.jobFavoritesDao.findByEmployeeId(employeeId),"Data listelendi");
    }

    @Override
    public Result addFavorite(int employeeId, int jobId) {

        if(!this.employeeDao.existsById(employeeId)){
            return new ErrorResult("Belə bir istifadəçi mövcud deyil!");
        }else if(!this.jobsDao.existsById(jobId)){
            return new ErrorResult("Belə bir İş elanı mövcud deyil!");
        }else if(this.jobFavoritesDao.existsByEmployee_IdAndJobs_Id(employeeId, jobId)){
            return new ErrorResult("Bu iş elanı artıq favorilərə əlavə olunub!");
        }

        JobFavorites jobFavorites =new JobFavorites();
        jobFavorites.setEmployee(this.employeeDao.getById(employeeId));
        jobFavorites.setJobs(this.jobsDao.getById(jobId));
        this.jobFavoritesDao.save(jobFavorites);
        return new SuccessResult("İş elanı favorilərə əlavə olundu!");
    }

    @Override
    public Result removeFavorite(int favoriteId) {
        if(!this.jobFavoritesDao.existsById(favoriteId)){
            return new ErrorResult("Belə bir elan favorilərdə mövcud deyildir!");
        }
        this.jobFavoritesDao.deleteById(favoriteId);
        return new SuccessResult("Elan favorilərdən silindi!");
    }
}
