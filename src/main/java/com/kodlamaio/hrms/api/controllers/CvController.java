package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.CvService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin
public class CvController {

    private CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }



    @GetMapping("/getall")
    public DataResult<List<Cv>> getAll(){
        return this.cvService.getAll();
    }

    @GetMapping("/getByCvId")
    public ResponseEntity<?> getByCvId(@RequestParam int cvId){
        DataResult<Cv> result=this.cvService.getByCvId(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getByEmployeeId")
    public ResponseEntity<?> getByEmployeeId(@RequestParam int employeeId){
        DataResult<Cv> result=this.cvService.getByEmployeeId(employeeId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateGithub")
    public ResponseEntity<?> updateGithub(@RequestParam String githublink,@RequestParam int cvId){
        Result result=this.cvService.updateGithub(githublink,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteGithub")
    public ResponseEntity<?> deleteGithub(@RequestParam int cvId){
        Result result=this.cvService.deleteGithub(cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateLinkedin")
    public ResponseEntity<?> updateLinkedin(@RequestParam String linkedinlink,@RequestParam int cvId){
        Result result=this.cvService.updateLinkedin(linkedinlink,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteLinkedin")
    public ResponseEntity<?> deleteLinkedin(@RequestParam int cvId){
        Result result=this.cvService.deleteLinkedin(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateBiography")
    public ResponseEntity<?> updateBiography(@RequestParam String biography,@RequestParam int cvId){
        Result result=this.cvService.updateBiography(biography,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteBiography")
    public ResponseEntity<?> deleteBiography(@RequestParam int cvId){
        Result result=this.cvService.deleteBiography(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
