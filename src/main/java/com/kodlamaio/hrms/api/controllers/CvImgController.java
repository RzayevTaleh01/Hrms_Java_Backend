package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.CvImgService;
import com.kodlamaio.hrms.core.services.CloudinaryService;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.dataAccess.abstracts.CvDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/Cv-Img")
@CrossOrigin
public class CvImgController {

    private CloudinaryService cloudinaryService;
    private CvImgService cvImgService;
    private CvDao cvDao;

    @Autowired
    public CvImgController(CloudinaryService cloudinaryService, CvImgService cvImgService, CvDao cvDao) {
        this.cloudinaryService = cloudinaryService;
        this.cvImgService = cvImgService;
        this.cvDao=cvDao;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.cvImgService.getAll());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile ,@RequestParam int cvId){
        Result result=this.cvImgService.update(multipartFile,cvId);
        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        Result result=this.cvImgService.delete(id);
        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
