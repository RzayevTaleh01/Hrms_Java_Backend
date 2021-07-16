package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.CvExperianceService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvExperiance;
import com.kodlamaio.hrms.entities.dto.CvExperianceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CvExperiance")
@CrossOrigin
public class CvExperianceController {

    private CvExperianceService cvExperianceService;

    @Autowired
    public CvExperianceController(CvExperianceService cvExperianceService) {
        this.cvExperianceService = cvExperianceService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CvExperianceDto cvExperianceDto){
        Result result = this.cvExperianceService.add(cvExperianceDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam int experianceId){
        return this.cvExperianceService.delete(experianceId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<CvExperiance>> getByCvId(@RequestParam int id){
        return this.cvExperianceService.getByCvId(id);
    }
}
