package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvPrgSkills;
import com.kodlamaio.hrms.entities.dto.CvPrgSkillsDto;

import java.util.List;

public interface CvPrgSkillsService {
    public Result addSkill(CvPrgSkillsDto cvPrgSkillsDto);
    public Result removeSkill(int skillId);
    public DataResult<List<CvPrgSkills>> getByCvId(int cvId);
}
