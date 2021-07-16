package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.JobCategoryService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.JobCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/JobCategory")
@CrossOrigin
public class JobCategoryController {

    private JobCategoryService jobCategoryService;

    @Autowired
    public JobCategoryController(JobCategoryService jobCategoryService) {
        this.jobCategoryService = jobCategoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobCategory>> getAll(){
        return this.jobCategoryService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobCategory jobCategory){
        Result result=this.jobCategoryService.add(jobCategory);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
