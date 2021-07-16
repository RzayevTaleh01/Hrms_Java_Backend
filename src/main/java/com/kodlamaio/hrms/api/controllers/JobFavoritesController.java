package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.JobFavoritesService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.JobFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobFavorites")
@CrossOrigin
public class JobFavoritesController {

    private JobFavoritesService jobFavoritesService;

    @Autowired
    public JobFavoritesController(JobFavoritesService jobFavoritesService) {
        this.jobFavoritesService = jobFavoritesService;
    }

    @GetMapping("/getByEmployeeId")
    public ResponseEntity<?> getByEmployeeId(@RequestParam int employeeId){
        DataResult<List<JobFavorites>> result=this.jobFavoritesService.getByEmployeeId(employeeId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<?> addFavorite(@RequestParam int employeeId,@RequestParam int jobsId){
        Result result=this.jobFavoritesService.addFavorite(employeeId,jobsId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/removeFavorite")
    public ResponseEntity<?> removeFavorite(@RequestParam int favoriteId){
        Result result = this.jobFavoritesService.removeFavorite(favoriteId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
