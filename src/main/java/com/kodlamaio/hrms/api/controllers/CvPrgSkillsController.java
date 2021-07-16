package com.kodlamaio.hrms.api.controllers;

import com.kodlamaio.hrms.busines.abstracts.CvPrgSkillsService;
import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvPrgSkills;
import com.kodlamaio.hrms.entities.dto.CvPrgSkillsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CvPrgSkills")
@CrossOrigin
public class CvPrgSkillsController {

    private CvPrgSkillsService cvPrgSkillsService;

    @Autowired
    public CvPrgSkillsController(CvPrgSkillsService cvPrgSkillsService) {
        this.cvPrgSkillsService = cvPrgSkillsService;
    }

    @PostMapping("/addSkill")
    public Result addSkill(@RequestBody CvPrgSkillsDto cvPrgSkillsDto){
        return this.cvPrgSkillsService.addSkill(cvPrgSkillsDto);
    }

    @DeleteMapping("/removeSkill")
    public Result removeSkill(@RequestParam int technologyId){
        return this.cvPrgSkillsService.removeSkill(technologyId);
    }

    @GetMapping("/getByCvId")
    public DataResult<List<CvPrgSkills>> getByCvId(@RequestParam int cvId){
        return this.cvPrgSkillsService.getByCvId(cvId);
    }
}
