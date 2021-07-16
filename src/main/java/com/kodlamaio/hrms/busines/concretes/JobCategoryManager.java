package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.JobCategoryService;
import com.kodlamaio.hrms.dataAccess.abstracts.JobCategoryDao;
import com.kodlamaio.hrms.entities.concretes.JobCategory;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryManager implements JobCategoryService {

    private JobCategoryDao jobCategoryDao;

    @Autowired
    public JobCategoryManager(JobCategoryDao jobCategoryDao) {
        this.jobCategoryDao = jobCategoryDao;
    }

    @Override
    public DataResult<List<JobCategory>> getAll() {
        return new SuccessDataResult<List<JobCategory>>(this.jobCategoryDao.findAll(),"Data listelendi");
    }

    @Override
    public Result add(JobCategory jobCategory) {
        if(getByName(jobCategory.getName()).getData() != null){
            return new ErrorResult("Bu isimde bir pozisyon zaten kayıtlı");
        }else if(jobCategory.getName().length() <=2){
            return new ErrorResult("İş ismi 2 karakterden kısa olamaz");
        }else{
            this.jobCategoryDao.save(jobCategory);
            return new SuccessResult("İş eklendi");
        }


    }

    @Override
    public DataResult<JobCategory> getByName(String name) {
        return new SuccessDataResult<JobCategory>(this.jobCategoryDao.findByName(name),"Listelendi");
    }
}
