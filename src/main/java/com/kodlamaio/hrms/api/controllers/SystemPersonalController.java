package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.SystemPersonalService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.SystemPersonal;
import com.kodlamaio.hrms.entities.dto.SystemPersonalUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/SystemPersonal")
@CrossOrigin
public class SystemPersonalController {

    private SystemPersonalService systemPersonalService;

    @Autowired
    public SystemPersonalController(SystemPersonalService systemPersonalService) {
        this.systemPersonalService = systemPersonalService;
    }

    @GetMapping("/getall")
    public DataResult<List<SystemPersonal>> getAll(){
        return this.systemPersonalService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody SystemPersonal systemPersonal){
        Result result=this.systemPersonalService.create(systemPersonal);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(SystemPersonalUpdateDto systemPersonalUpdateDto){
        Result result=this.systemPersonalService.update(systemPersonalUpdateDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
