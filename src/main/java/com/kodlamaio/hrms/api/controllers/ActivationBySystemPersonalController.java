package com.kodlamaio.hrms.api.controllers;


import com.kodlamaio.hrms.busines.abstracts.ActivationBySystemPersonalService;
import com.kodlamaio.hrms.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ActivationBySystemPersonal")
@CrossOrigin
public class ActivationBySystemPersonalController {

    private ActivationBySystemPersonalService activationBySystemPersonalService;

    @Autowired
    public ActivationBySystemPersonalController(ActivationBySystemPersonalService activationBySystemPersonalService) {
        this.activationBySystemPersonalService = activationBySystemPersonalService;
    }

    @GetMapping("/activateemployer")
    public ResponseEntity<?> activateEmployer(@RequestParam int employerId,@RequestParam int staffId){
        Result result=this.activationBySystemPersonalService.activateEmployer(employerId, staffId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
