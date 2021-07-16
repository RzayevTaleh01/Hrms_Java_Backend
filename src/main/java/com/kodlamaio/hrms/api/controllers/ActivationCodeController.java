package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.ActivationCodeService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activationcode")
@CrossOrigin
public class ActivationCodeController {
    private ActivationCodeService activationCodeService;

    @Autowired
    public ActivationCodeController(ActivationCodeService activationCodeService) {
        this.activationCodeService = activationCodeService;
    }

    @GetMapping("/getAll")
    public DataResult<List<ActivationCode>> getAll(){
        return this.activationCodeService.getAll();
    }
    @GetMapping("/active/{code}")
    public ResponseEntity<?> activateUser(@PathVariable String code){
        Result result=this.activationCodeService.activateUser(code);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
