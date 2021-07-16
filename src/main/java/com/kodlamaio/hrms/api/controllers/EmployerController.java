package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.EmployerService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.Employer;
import com.kodlamaio.hrms.entities.concretes.EmployerUpdate;
import com.kodlamaio.hrms.entities.dto.EmployerRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
@CrossOrigin
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll(){
        return this.employerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployerRegisterDto employerRegisterDto){
        Result result=this.employerService.add(employerRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getById")
    DataResult<Employer> getById(@RequestParam int id){
        return this.employerService.getById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EmployerUpdate employerUpdate){
        Result result = this.employerService.update(employerUpdate);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/verifyUpdate")
    public ResponseEntity<?> verifyUpdate(@RequestParam int employerUpdateId,@RequestParam int staffId){
        Result result = this.employerService.verifyUpdate(employerUpdateId,staffId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
