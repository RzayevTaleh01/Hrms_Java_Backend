package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.CvSchoolService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvSchool;
import com.kodlamaio.hrms.entities.dto.CvSchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CvSchool")
@CrossOrigin
public class CvSchoolController {

    private CvSchoolService cvSchoolService;

    @Autowired
    public CvSchoolController(CvSchoolService cvSchoolService) {
        this.cvSchoolService = cvSchoolService;
    }

    @PostMapping("/addSchool")
    public Result addSchool(@RequestBody CvSchoolDto cvSchoolDto){
        return this.cvSchoolService.addSchool(cvSchoolDto);
    }

    @DeleteMapping("/deleteSchool")
    public Result deleteSchool(@RequestParam int schoolId){
        return this.cvSchoolService.deleteSchool(schoolId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<CvSchool>> getByCvId(@RequestParam int cvId){
        return this.cvSchoolService.getByCvId(cvId);
    }
}
