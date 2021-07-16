package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.JobsService;
import com.kodlamaio.hrms.entities.concretes.Jobs;
import com.kodlamaio.hrms.entities.concretes.JobsActivation;
import com.kodlamaio.hrms.entities.dto.JobsDto;
import com.kodlamaio.hrms.entities.dto.JobsFilter;
import com.kodlamaio.hrms.core.utilities.results.*;
import com.kodlamaio.hrms.dataAccess.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobsManager implements JobsService {

    private JobsDao jobsDao;
    private JobCategoryDao jobCategoryDao;
    private EmployerDao employerDao;
    private CityDao cityDao;
    private WorkPlaceDao workPlaceDao;
    private WorkTimeDao workTimeDao;
    private JobsActivationDao jobsActivationDao;
    private SystemPersonalDao systemPersonalDao;

    @Autowired
    public JobsManager(JobsDao jobsDao, JobCategoryDao jobCategoryDao, EmployerDao employerDao, CityDao cityDao, WorkPlaceDao workPlaceDao, WorkTimeDao workTimeDao, JobsActivationDao jobsActivationDao, SystemPersonalDao systemPersonalDao) {
        this.jobsDao = jobsDao;
        this.jobCategoryDao = jobCategoryDao;
        this.employerDao=employerDao;
        this.cityDao=cityDao;
        this.workPlaceDao=workPlaceDao;
        this.workTimeDao=workTimeDao;
        this.jobsActivationDao = jobsActivationDao;
        this.systemPersonalDao = systemPersonalDao;
    }

    @Override
    public Result create(JobsDto jobsDto) {

        if(!cityDao.existsById(jobsDto.getCityId())){
            return new ErrorResult("Şəhər Tapılmadı!");
        }
        else if(!this.employerDao.existsById(jobsDto.getEmployerId())){
            return new ErrorResult("Şirkət Tapılmadı!");
        }
        else if(jobsDto.getDescription().isEmpty()){
            return new ErrorResult("İşin Təsviri boş verilə bilməz");
        }
        else if(jobsDto.getMinSalary()==0){
            return new ErrorResult("Minumum maaş 0 ola bilməz!");
        }
        else if(jobsDto.getMaxSalary()==0){
            return new ErrorResult("Maximum maaş 0 ola bilməz!");
        }
        else if(jobsDto.getMinSalary() >= jobsDto.getMaxSalary()){
            return new ErrorResult("Minumum maaş maksimum maaşa bərabər və ya böyük ola bilməz!");
        }
        else if(jobsDto.getOpenPositions()<1){
            return new ErrorResult("İş üçün ayrılan vakat yer 1 dən kiçik ola bilməz!");
        }
        else if(jobsDto.getLastDate() == null){
            return new ErrorResult("Son tarix boş ola bilməz!");
        }else if(!this.workPlaceDao.existsById(jobsDto.getWorkPlaceId())){
            return new ErrorResult("İş Formatı düzgün seçilmədi!");
        }else if(!this.workTimeDao.existsById(jobsDto.getWorkTimeId())){
            return new ErrorResult("İş Saatı düzgün seçilmədi!");
        }


        Jobs jobs =new Jobs();
        jobs.setId(0);
        jobs.setName(jobsDto.getName());
        jobs.setJobCategory(this.jobCategoryDao.getById(jobsDto.getJobPositionId()));
        jobs.setEmployer(this.employerDao.getById(jobsDto.getEmployerId()));
        jobs.setDescription(jobsDto.getDescription());
        jobs.setCity(this.cityDao.getById(jobsDto.getCityId()));
        jobs.setMinSalary(jobsDto.getMinSalary());    
        jobs.setMaxSalary(jobsDto.getMaxSalary());
        jobs.setOpenPositions(jobsDto.getOpenPositions());
        jobs.setLastDate(jobsDto.getLastDate());
        jobs.setActive(false);
        jobs.setCreateDate(LocalDate.now());
        jobs.setWorkPlace(this.workPlaceDao.getById(jobsDto.getWorkPlaceId()));
        jobs.setWorkTime(this.workTimeDao.getById(jobsDto.getWorkTimeId()));
        jobs.setConfirmed(false);
        this.jobsDao.save(jobs);

        JobsActivation jobsActivation =new JobsActivation();
        jobsActivation.setJobId(jobs.getId());
        jobsActivation.setConfirm(false);
        this.jobsActivationDao.save(jobsActivation);


        return new SuccessResult("Elan uğurla əlavə olundu!");
    }

    @Override
    public Result setPasssive(int jobAdId) {
        try {
            Jobs jobs =this.jobsDao.getById(jobAdId);
            jobs.setActive(false);
            jobsDao.save(jobs);
            return new SuccessResult("Elan deaktiv edildi!");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("Elan Tapılmadı!");
        }

    }

    @Override
    public Result setActiveAndConfirm(int jobAdId,int staffId) {
        try{
            if(!this.systemPersonalDao.existsById(staffId)){
                return new ErrorResult("Belə bir sistem işçisi mövcud deyil!");
            }
            JobsActivation jobsActivation =this.jobsActivationDao.getById(jobAdId);
            jobsActivation.setConfirmDate(LocalDate.now());
            jobsActivation.setConfirm(true);
            jobsActivation.setSpİd(staffId);
            this.jobsActivationDao.save(jobsActivation);

            Jobs jobs =this.jobsDao.getById(jobAdId);
            jobs.setActive(true);
            jobs.setConfirmed(true);
            this.jobsDao.save(jobs);
            return new SuccessResult("Elan Aktiv Edildi!");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("Elan Tapılmadı");
        }

    }

    @Override
    public DataResult<List<Jobs>> getAll() {
        return new SuccessDataResult<List<Jobs>>(this.jobsDao.findAll(),"Data listələndi");
    }

    @Override
    public DataResult<Jobs> getByJobId(int id) {
        if(!this.jobsDao.existsById(id)){
            return new ErrorDataResult<Jobs>("Belə bir elan mövcud deyil!");
        }
        return new SuccessDataResult<Jobs>(this.jobsDao.getById(id),"Data listələndi");
    }


    @Override
    public DataResult<List<Jobs>> getActiveJobs() {
        return new SuccessDataResult<List<Jobs>>(this.jobsDao.findByActive(true),"Aktiv iş elanları listələndi");
    }

    @Override
    public DataResult<List<Jobs>> getActiveAndOrderLastDate() {
        return new SuccessDataResult<List<Jobs>>(this.jobsDao.findByActiveOrderByLastDate(true),"Aktiv iş elanları tarixə görə listələndi");
    }

    @Override
    public DataResult<List<Jobs>> getActiveAndCompanyId(int id) {
        return new SuccessDataResult<List<Jobs>>(this.jobsDao.findByActiveTrueAndEmployer_Id(id),"Şirkətə görə aktiv iş elanları listələndi");
    }

    @Override
    public DataResult<List<Jobs>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobsFilter jobsFilter) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return new SuccessDataResult<List<Jobs>>(this.jobsDao.getByFilter(jobsFilter, pageable).getContent(), this.jobsDao.getByFilter(jobsFilter,pageable).getTotalElements()+"");
    }


}
