package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.JobsService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import com.kodlamaio.hrms.entities.concretes.Jobs;
import com.kodlamaio.hrms.entities.dto.JobsDto;
import com.kodlamaio.hrms.entities.dto.JobsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Jobs")
@CrossOrigin
public class JobsController {

    private JobsService jobsService;

    @Autowired
    public JobsController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping("/getall")
    public DataResult<List<Jobs>> getAll(){
        return this.jobsService.getAll();
    }

    @GetMapping("/getByJobId")
    public DataResult<Jobs> getByJobAdId(@RequestParam int id){
        Jobs jobs =new Jobs();
        Jobs jobsForSet =this.jobsService.getByJobId(id).getData();
        if(jobsForSet ==null){
            return new ErrorDataResult<Jobs>("İş Elanı tapılmadı");
        }
        jobs.setId(jobsForSet.getId());
        jobs.setEmployer(jobsForSet.getEmployer());
        jobs.setJobCategory(jobsForSet.getJobCategory());
        jobs.setDescription(jobsForSet.getDescription());
        jobs.setCity(jobsForSet.getCity());
        jobs.setMinSalary(jobsForSet.getMinSalary());
        jobs.setMaxSalary(jobsForSet.getMaxSalary());
        jobs.setOpenPositions(jobsForSet.getOpenPositions());
        jobs.setLastDate(jobsForSet.getLastDate());
        jobs.setActive(jobsForSet.isActive());
        jobs.setCreateDate(jobsForSet.getCreateDate());
        jobs.setWorkPlace(jobsForSet.getWorkPlace());
        jobs.setWorkTime(jobsForSet.getWorkTime());
        jobs.setConfirmed(jobsForSet.isConfirmed());

        return new SuccessDataResult<Jobs>(jobs,"Data listələndi");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JobsDto jobsDto){
        Result result=this.jobsService.create(jobsDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/setPassive")
    public ResponseEntity<?> setPasssive(@RequestParam int jobAdId){
        Result result=this.jobsService.setPasssive(jobAdId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/setActive")
    public ResponseEntity<?> setActiveAndConfirm(@RequestParam int jobAdId,@RequestParam int staffId){
        Result result=this.jobsService.setActiveAndConfirm(jobAdId,staffId);
        if(!result.isSuccess()){
            ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getActiveJobs")
    public DataResult<List<Jobs>> getActiveJobs(){
        return this.jobsService.getActiveJobs();
    }

    @GetMapping("/getActivesOrderLastDate")
    public DataResult<List<Jobs>> getActivAndOrderLastDate(){
        return this.jobsService.getActiveAndOrderLastDate();
    }

    @GetMapping("/getActiveAndCompanyId")
    public DataResult<List<Jobs>> getActiveAndCompanyId(@RequestParam int companyId){
        return this.jobsService.getActiveAndCompanyId(companyId);
    }

    @PostMapping("/getByActiveAndFilter")
    public Result getByActiveAndFilter(@RequestParam int pageNo,@RequestParam int pageSize,@RequestBody JobsFilter jobsFilter){
        return jobsService.getByIsActiveAndPageNumberAndFilter(pageNo,pageSize, jobsFilter);
    }
}
