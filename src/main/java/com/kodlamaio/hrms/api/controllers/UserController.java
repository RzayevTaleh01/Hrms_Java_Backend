package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.UserService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.entities.concretes.User;
import com.kodlamaio.hrms.entities.dto.UserLoginDto;
import com.kodlamaio.hrms.entities.dto.UserLoginReturnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<User>> getAll(){
        return this.userService.getAll();
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
        DataResult<UserLoginReturnDto> result = this.userService.login(userLoginDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/getVerifyedUsers")
    DataResult<List<User>> getVerifyedUsers(){
        return this.userService.getVerifyedUsers();
    }
}
